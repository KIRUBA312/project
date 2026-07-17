package com.example.api_monetization.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api_monetization.dto.billing.BillingSummaryResponse;
import com.example.api_monetization.dto.billing.InvoiceResponse;
import com.example.api_monetization.dto.billing.PaymentRequest;
import com.example.api_monetization.dto.billing.PaymentResponse;
import com.example.api_monetization.entity.BillingCycleEntity;
import com.example.api_monetization.entity.DeveloperSubscription;
import com.example.api_monetization.entity.Invoice;
import com.example.api_monetization.entity.Payment;
import com.example.api_monetization.entity.SubscriptionPlan;
import com.example.api_monetization.enums.InvoiceStatus;
import com.example.api_monetization.exception.ResourceAlreadyExistsException;
import com.example.api_monetization.exception.ResourceNotFoundException;
import com.example.api_monetization.mapper.BillingMapper;
import com.example.api_monetization.repository.BillingCycleRepository;
import com.example.api_monetization.repository.DeveloperSubscriptionRepository;
import com.example.api_monetization.repository.InvoiceRepository;
import com.example.api_monetization.repository.PaymentRepository;
import com.example.api_monetization.service.BillingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BillingServiceImpl implements BillingService{

	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private BillingCycleRepository billingCycleRepository;
	@Autowired
	private DeveloperSubscriptionRepository developerSubscriptionRepository;
	@Autowired
	private BillingMapper billingMapper;
	@Override
	public InvoiceResponse generateInvoice(Long subscriptionId) {
		// TODO Auto-generated method stub
		DeveloperSubscription subscription = developerSubscriptionRepository
				.findById(subscriptionId).orElseThrow(() ->
				new ResourceNotFoundException("Subscription not found"));
		
		BillingCycleEntity cycle = billingCycleRepository
				.findCurrentBillingCycle(LocalDate.now())
				.orElseThrow(() ->new ResourceNotFoundException(
						"Billing Cycle not found"));
		
		SubscriptionPlan plan = subscription.getPlan();
		
		Invoice invoice = new Invoice();
		invoice.setSubscription(subscription);
		invoice.setBillingCycle(cycle);
		invoice.setInvoiceNumber(generateInvoiceNumber());
		invoice.setInvoiceDate(LocalDate.now());
		invoice.setDueDate(LocalDate.now().plusDays(15));
		
		BigDecimal subtotal = subscription.getBillingCycle().name()
				.equals("MONTHLY")?
						plan.getMonthlyPrice():plan.getYearlyPrice();
		invoice.setSubtotal(subtotal);
		
		BigDecimal tax = subtotal.multiply(new BigDecimal("0.18"));
		invoice.setTaxAmount(tax);
		invoice.setTotalAmount(subtotal.add(tax));
		Invoice saved = invoiceRepository.save(invoice);
		return billingMapper.toInvoiceResponse(saved);
	}
	private String generateInvoiceNumber() {

	    return "INV-"
	            + System.currentTimeMillis();
	}
	@Override
	public InvoiceResponse getInvoice(Long invoiceId) {
		// TODO Auto-generated method stub
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() ->
				new ResourceNotFoundException("Invoice not found"));
		return billingMapper.toInvoiceResponse(invoice);
	}
	@Override
	public List<InvoiceResponse> getSubscriptionInvoices(Long subscriptionId) {
		// TODO Auto-generated method stub
		if(!developerSubscriptionRepository.existsById(subscriptionId)) {
			throw new ResourceNotFoundException(
					"Subscription not found");
			
		}
		return invoiceRepository.findBySubscriptionId(subscriptionId)
				.stream().map(billingMapper::toInvoiceResponse)
				.toList();
	}
	@Override
	public PaymentResponse makePayment(@Valid PaymentRequest request) {
		// TODO Auto-generated method stub
	    Invoice invoice = invoiceRepository.findById(request.getInvoiceId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Invoice not found."));

	    if (paymentRepository.findById(invoice.getId()).isPresent()) {

	        throw new ResourceAlreadyExistsException(
	                "Payment already exists for this invoice.");
	    }

	    Payment payment = new Payment();
	    payment.setInvoice(invoice);
	    payment.setPaymentReference(generatePaymentReference());
	    payment.setPaymentMethod(request.getPaymentMethod());
	    payment.setAmount(invoice.getTotalAmount());
	    payment.setPaymentStatus(request.getPaymentStatus());
	    payment.setPaymentDate(LocalDateTime.now());
	    payment.setTransactionId(generateTransactionId());
	    Payment saved = paymentRepository.save(payment);
	    invoice.setInvoiceStatus(InvoiceStatus.PAID);

	    invoiceRepository.save(invoice);
		return billingMapper.toPaymentResponse(saved);
	}
	private String generateTransactionId() {
		// TODO Auto-generated method stub
		return "TXN-"+System.nanoTime();
	}
	private String generatePaymentReference() {
		// TODO Auto-generated method stub
		return "PAY-"+System.currentTimeMillis();
	}
	@Override
	public PaymentResponse getPayment(Long invoiceId) {
		// TODO Auto-generated method stub
		Payment payment = paymentRepository.findById(invoiceId)
				.orElseThrow(() ->
				new ResourceNotFoundException("Payment not found"));
		return billingMapper.toPaymentResponse(payment);
	}
	@Override
	public BillingSummaryResponse getBillingSummary(Long subscriptionId) {
		// TODO Auto-generated method stub 
		if (!developerSubscriptionRepository.existsById(subscriptionId)) {

        throw new ResourceNotFoundException(
                "Subscription not found.");
    }

    BillingSummaryResponse response =
            new BillingSummaryResponse();

    response.setSubscriptionId(subscriptionId);

    long totalInvoices =
            invoiceRepository.countBySubscriptionId(subscriptionId);

    long paidInvoices =
            invoiceRepository.countBySubscriptionIdAndInvoiceStatus(
                    subscriptionId,
                    InvoiceStatus.PAID);

    long pendingInvoices =
            invoiceRepository.countBySubscriptionIdAndInvoiceStatus(
                    subscriptionId,
                    InvoiceStatus.PENDING);
    long overdueInvoices = 
    		invoiceRepository.countBySubscriptionIdAndInvoiceStatus(
    				subscriptionId,
    				InvoiceStatus.OVERDUE);

    response.setTotalInvoices(totalInvoices);
    response.setPaidInvoices(paidInvoices);
    response.setPendingInvoices(pendingInvoices);
    response.setOverdueInvoices(overdueInvoices);

    List<Invoice> invoices =
            invoiceRepository.findBySubscriptionId(subscriptionId);

    BigDecimal totalAmount = invoices.stream()
            .map(Invoice::getTotalAmount)
            .reduce(BigDecimal.ZERO, 
            		BigDecimal::add);

    BigDecimal paidAmount =
            invoiceRepository
                    .findBySubscriptionIdAndInvoiceStatus(
                            subscriptionId,
                            InvoiceStatus.PAID)
                    .stream()
                    .map(Invoice::getTotalAmount)
                    .reduce(BigDecimal.ZERO, 
                    		BigDecimal::add);
    BigDecimal totalPendingAmount = invoiceRepository
    		.findBySubscriptionIdAndInvoiceStatus(subscriptionId, 
    				InvoiceStatus.PENDING).stream()
    		.map(Invoice::getTotalAmount)
    		.reduce(BigDecimal.ZERO, 
    				BigDecimal::add);

    response.setTotalRevenue(totalAmount);
    response.setTotalPaidAmount(paidAmount);
    response.setTotalPendingAmount(totalPendingAmount);

    return response;
		
	}
	
	
	
	
}

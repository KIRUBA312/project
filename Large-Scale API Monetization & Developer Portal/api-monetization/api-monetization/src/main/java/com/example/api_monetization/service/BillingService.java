package com.example.api_monetization.service;

import java.util.List;

import com.example.api_monetization.dto.billing.BillingSummaryResponse;
import com.example.api_monetization.dto.billing.InvoiceResponse;
import com.example.api_monetization.dto.billing.PaymentRequest;
import com.example.api_monetization.dto.billing.PaymentResponse;

import jakarta.validation.Valid;

public interface BillingService {

	InvoiceResponse generateInvoice(Long subscriptionId);

	InvoiceResponse getInvoice(Long invoiceId);

	List<InvoiceResponse> getSubscriptionInvoices(Long subscriptionId);

	PaymentResponse makePayment(@Valid PaymentRequest request);

	PaymentResponse getPayment(Long invoiceId);

	BillingSummaryResponse getBillingSummary(Long subscriptionId);

}

package com.example.api_monetization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_monetization.dto.billing.BillingSummaryResponse;
import com.example.api_monetization.dto.billing.InvoiceResponse;
import com.example.api_monetization.dto.billing.PaymentRequest;
import com.example.api_monetization.dto.billing.PaymentResponse;
import com.example.api_monetization.service.BillingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {

	@Autowired
	private BillingService billingService;
	
	@PostMapping("/invoices/generate/{subscriptionId}")
	public ResponseEntity<InvoiceResponse> generateInvoice(
			@PathVariable Long subscriptionId){
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(billingService.generateInvoice(subscriptionId));
	}
	
	@GetMapping("/invoices/{invoiceId}")
    public ResponseEntity<InvoiceResponse> getInvoice(
            @PathVariable Long invoiceId) {

        return ResponseEntity.ok(
                billingService.getInvoice(invoiceId));
    }

    @GetMapping("/invoices/subscription/{subscriptionId}")
    public ResponseEntity<List<InvoiceResponse>> getSubscriptionInvoices(
            @PathVariable Long subscriptionId) {

        return ResponseEntity.ok(
                billingService.getSubscriptionInvoices(subscriptionId));
    }


    @PostMapping("/payments")
    public ResponseEntity<PaymentResponse> makePayment(
            @Valid @RequestBody PaymentRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(billingService.makePayment(request));
    }

    @GetMapping("/payments/{invoiceId}")
    public ResponseEntity<PaymentResponse> getPayment(
            @PathVariable Long invoiceId) {

        return ResponseEntity.ok(
                billingService.getPayment(invoiceId));
    }

    @GetMapping("/summary/{subscriptionId}")
    public ResponseEntity<BillingSummaryResponse> getBillingSummary(
            @PathVariable Long subscriptionId) {

        return ResponseEntity.ok(
                billingService.getBillingSummary(subscriptionId));
    }
	
	
}

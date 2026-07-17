package com.example.api_monetization.mapper;

import org.mapstruct.Mapper;

import com.example.api_monetization.dto.billing.InvoiceResponse;
import com.example.api_monetization.dto.billing.PaymentResponse;
import com.example.api_monetization.entity.Invoice;
import com.example.api_monetization.entity.Payment;

@Mapper(config = MapperConfig.class)
public interface BillingMapper {
	
	InvoiceResponse toInvoiceResponse(Invoice invoice);
	PaymentResponse toPaymentResponse(Payment payment);

}

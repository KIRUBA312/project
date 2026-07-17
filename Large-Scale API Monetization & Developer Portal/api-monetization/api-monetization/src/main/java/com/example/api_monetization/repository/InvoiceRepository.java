package com.example.api_monetization.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_monetization.entity.Invoice;
import com.example.api_monetization.enums.InvoiceStatus;
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	List<Invoice> findBySubscriptionId(Long subscriptionId);
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    
    long countBySubscriptionId(Long subscriptionId);

    long countBySubscriptionIdAndInvoiceStatus(
            Long subscriptionId,
            InvoiceStatus invoiceStatus);

    List<Invoice> findBySubscriptionIdAndInvoiceStatus(
            Long subscriptionId,
            InvoiceStatus invoiceStatus);

}
package com.example.api_monetization.dto.billing;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillingSummaryResponse {
	
	private Long subscriptionId;

    private Long totalInvoices;

    private Long paidInvoices;

    private Long pendingInvoices;

    private Long overdueInvoices;

    private BigDecimal totalRevenue;

    private BigDecimal totalPendingAmount;

    private BigDecimal totalPaidAmount;

}
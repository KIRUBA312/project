package com.example.api_monetization.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "api_access_audit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiAccessAudit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_usage_id")
    private ApiUsageLog apiUsage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_key_id")
    private ApiKey apiKey;

    @Column(name = "action", length = 50)
    private String action;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;
}
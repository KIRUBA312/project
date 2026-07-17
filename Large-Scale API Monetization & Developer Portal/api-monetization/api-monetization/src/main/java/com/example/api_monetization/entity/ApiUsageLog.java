package com.example.api_monetization.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "api_usage_logs")
@Getter
@Setter
@NoArgsConstructor
public class ApiUsageLog extends BaseEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_id", nullable = false)
    private Api api;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ConsumerApplication application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private DeveloperSubscription subscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    @Column(name = "request_timestamp")
    private LocalDateTime requestTimestamp;

    @Column(name = "http_method", length = 20)
    private String httpMethod;

    @Column(name = "endpoint", length = 500)
    private String endpoint;

    @Column(name = "response_status")
    private Integer responseStatus;

    @Column(name = "response_time_ms")
    private Long responseTimeMs;

    @Column(name = "request_size")
    private Long requestSize;

    @Column(name = "response_size")
    private Long responseSize;

    @Column(name = "client_ip", length = 100)
    private String clientIp;

    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;

    @Column(name = "success")
    private Boolean success = true;

    @OneToMany(mappedBy = "usageLog", cascade = CascadeType.ALL)
    private List<ApiRequestLog> requestLogs;

    @OneToMany(mappedBy = "usageLog", cascade = CascadeType.ALL)
    private List<KafkaUsageEvent> kafkaEvents;

    @OneToMany(mappedBy = "apiUsage", cascade = CascadeType.ALL)
    private List<ApiAccessAudit> accessAudits;

}
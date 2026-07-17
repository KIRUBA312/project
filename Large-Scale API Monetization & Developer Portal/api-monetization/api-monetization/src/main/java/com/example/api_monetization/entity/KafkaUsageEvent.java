package com.example.api_monetization.entity;

import java.time.LocalDateTime;

import com.example.api_monetization.enums.UsageEventStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kafka_usage_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaUsageEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usage_log_id", nullable = false)
    private ApiUsageLog usageLog;

    @Column(name = "topic_name", nullable = false)
    private String topicName;

    @Column(name = "partition_no")
    private Integer partitionNo;

    @Column(name = "event_key")
    private String eventKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_status")
    private UsageEventStatus eventStatus;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;
}
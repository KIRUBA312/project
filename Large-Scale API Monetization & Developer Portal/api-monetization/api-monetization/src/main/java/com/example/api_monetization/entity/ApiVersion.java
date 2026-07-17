package com.example.api_monetization.entity;
import java.time.LocalDateTime;

import com.example.api_monetization.enums.ApiKeyStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "api_versions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiVersion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "api_id", nullable = false)
	@JsonBackReference
	private Api api;
	
    @Column(name = "version_name", nullable = false, length = 30)
    private String versionName;

    @Column(length = 500)
    private String endpoint;

    @Column(name = "swagger_url", length = 500)
    private String swaggerUrl;

    @Column(name = "openapi_url", length = 500)
    private String openapiUrl;

    @Column(name = "release_notes", columnDefinition = "TEXT")
    private String releaseNotes;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private ApiKeyStatus status;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Column(name = "deprecated_date")
    private LocalDateTime deprecatedDate;

    @Column(name = "retired_date")
    private LocalDateTime retiredDate;

}

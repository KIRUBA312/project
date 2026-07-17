package com.example.api_monetization.entity;
import java.util.ArrayList;
import java.util.List;

import com.example.api_monetization.enums.ApiLifecycleStatus;
import com.example.api_monetization.enums.ApiVisibility;
import com.example.api_monetization.enums.AuthenticationType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "apis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Api {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@JsonBackReference
	private ApiCategory category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publisher_id",nullable = false)
	private User publisher;
	
	@Column(name = "api_name", nullable = false, length = 200)
	private String apiName;
	
	@Column(name = "display_name",length = 255)
	private String displayName;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "base_url", nullable = false, length = 500)
	private String baseUrl;
	
	@Column(name = "current_version", length = 30)
	private String currentVersion;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private ApiVisibility visibility;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "lifecycle_status", length = 30)
	private ApiLifecycleStatus lifecycleStatus;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "authentication_type", length = 50)
    private AuthenticationType authenticationType;

    @Column(name = "rate_limit_per_minute")
    private Integer rateLimitPerMinute;

    @OneToMany(mappedBy = "api")
    @JsonManagedReference
    @Builder.Default
    private List<ApiVersion> versions = new ArrayList<>();

    @OneToMany(mappedBy = "api")
    @Builder.Default
    private List<ApiDocumentation> documentations = new ArrayList<>();

    @OneToMany(mappedBy = "api")
    @Builder.Default
    private List<ApiPublishRequest> publishRequests = new ArrayList<>();

    @OneToMany(mappedBy = "api")
    @Builder.Default
    private List<ApiTagMapping> tagMappings = new ArrayList<>();
}

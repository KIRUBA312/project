package com.example.api_monetization.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.api_monetization.enums.ApiKeyStatus;

@Entity
@Table(name = "api_keys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiKey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "application_id",nullable = false,
	foreignKey = @ForeignKey(name = "fk_api_key_application")
	)
	private ConsumerApplication application;
	
	@Column(name = "api_key",nullable = false, unique = true, length = 255)
	private String apiKey;
	
	@Column(name = "api_secret", nullable = false, length = 255)
	private String apiSecret;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 30)
	private ApiKeyStatus status;
	
	@Column(name = "generated_at")
	private LocalDateTime generatedAt;
	
	@Column(name = "expires_at")
	private LocalDateTime expiresAt;
	
	@Column(name = "regenerated_at")
	private LocalDateTime regeneratedAt;
	
}

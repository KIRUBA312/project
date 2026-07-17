package com.example.api_monetization.entity;
import com.example.api_monetization.enums.AccountStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "consumer_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumerApplication extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "developer_id",nullable = false,
	foreignKey = @ForeignKey(name = "fk_application_developer"))
	private DeveloperProfile developer;
	
	@Column(name = "application_name", nullable = false, length = 200)
	private String applicationName;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "redirect_url",length = 500)
	private String redirectUrl;
	
	@Column(name = "callback_url",length = 500)
	private String callbackUrl;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 30)
	private AccountStatus status;
	
}

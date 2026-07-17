package com.example.api_monetization.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "api_documentation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiDocumentation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "api_id",nullable = false)
	private Api api;
	
	@Column(columnDefinition = "TEXT")
	private String overview;
	
	@Column(columnDefinition = "TEXT")
	private String authentication;
	
	@Column(name = "request_example",columnDefinition = "TEXT")
	private String requestExample;
	
	@Column(name = "response_example", columnDefinition = "TEXT")
	private String responseExample;
	
	@Column(name = "error_codes", columnDefinition = "TEXT")
	private String errorCodes;
	
	
}

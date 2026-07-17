package com.example.api_monetization.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "api_tag_mapping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiTagMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "api_id",nullable = false)
	private Api api;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id", nullable = false)
	private ApiTag tag;
	
	
}

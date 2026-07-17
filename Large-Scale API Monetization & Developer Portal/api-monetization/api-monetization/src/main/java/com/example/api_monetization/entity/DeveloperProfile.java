package com.example.api_monetization.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "developer_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeveloperProfile extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable = false,unique = true,
	foreignKey = @ForeignKey(name = "fk_developer_user"))
	private User user;
	
	@Column(name = "company_name",length = 200)
	private String companyName;
	
	@Column(name = "website", length = 255)
	private String website;
	
	@Column(name = "address", columnDefinition = "TEXT")
	private String address;
	
	@Column(name = "city", length = 100)
	private String city;
	
	@Column(name = "state", length=100)
	private String state;
	
	@Column(name = "country",length = 100)
	private String country;
	
	@Column(name = "postal_code", length = 20)
	private String postalCode;
	
		
}

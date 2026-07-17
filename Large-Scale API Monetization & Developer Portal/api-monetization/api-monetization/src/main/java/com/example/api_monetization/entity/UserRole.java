package com.example.api_monetization.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_roles",
		uniqueConstraints = {
				@UniqueConstraint(
						name = "uk_user_role",
						columnNames = {"user_id","role_id"}
						)
		}
	)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable = false,
				foreignKey = @ForeignKey(name="fk_user_role_user"))
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id",nullable = false,
				foreignKey = @ForeignKey(name = "fk_user_role_role"))
	private Role role;
	
}

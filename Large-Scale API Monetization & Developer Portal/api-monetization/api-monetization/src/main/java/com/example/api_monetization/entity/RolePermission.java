package com.example.api_monetization.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
		name = "role_permissions",
		uniqueConstraints = {
				@UniqueConstraint(
						name="uk_role_permission",
						columnNames = {"role_id","permission_id"} 
				)
		}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermission extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id",nullable = false,
	foreignKey = @ForeignKey(name = "fk_role_permission_role"))
	private Role role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id",nullable = false,
		foreignKey = @ForeignKey(name = "fk_role_permission_permission")
	)
	private Permission permission;
	
	
}

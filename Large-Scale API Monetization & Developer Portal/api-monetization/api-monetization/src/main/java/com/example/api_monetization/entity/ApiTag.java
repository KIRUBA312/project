package com.example.api_monetization.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "api_tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tag_name",nullable = false, unique = true, length = 100)
	private String tagName;
	
	@OneToMany(mappedBy = "tag")
	@Builder.Default
	private List<ApiTagMapping> mappings = new ArrayList<>();
	
	
}

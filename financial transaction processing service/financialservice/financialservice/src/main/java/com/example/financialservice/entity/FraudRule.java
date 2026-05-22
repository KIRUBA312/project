package com.example.financialservice.entity;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "fraud_rule")
public class FraudRule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rule_id")
	private Long ruleId;
	
	@Column(name = "rule_name")
	private String ruleName;
	
	@Column(name = "max_amount_limit")
	private BigDecimal maxAmountLimit;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	public FraudRule() {}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public BigDecimal getMaxAmountLimit() {
		return maxAmountLimit;
	}

	public void setMaxAmountLimit(BigDecimal maxAmountLimit) {
		this.maxAmountLimit = maxAmountLimit;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

}

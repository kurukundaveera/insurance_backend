package com.hcl.einsurance.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyResponseDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer policyId;
	private String policyName;
	private Integer policyMinAge;
	private Integer policyMaxAge;
	private Double policyPrice;
}

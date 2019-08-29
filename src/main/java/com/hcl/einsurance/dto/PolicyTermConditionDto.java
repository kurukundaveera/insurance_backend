package com.hcl.einsurance.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyTermConditionDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String description;
	private String modesOfPremium;
	private String taxbenefit;
	private String policyRevival;
	private String loneFacility;

}

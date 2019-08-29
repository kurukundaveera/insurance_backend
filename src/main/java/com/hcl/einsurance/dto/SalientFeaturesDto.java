package com.hcl.einsurance.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalientFeaturesDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String minimumSumAssured;
	private String policyTerm;
	private String paymentMode;
	private String incomeTaxBenefit;
}

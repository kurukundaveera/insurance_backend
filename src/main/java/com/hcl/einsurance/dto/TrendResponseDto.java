package com.hcl.einsurance.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author DeepikaSivarajan
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrendResponseDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer policyId;
	private String policyName;
	private Long count;
	private Double percentage;

}

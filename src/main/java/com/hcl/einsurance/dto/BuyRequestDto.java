package com.hcl.einsurance.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BuyRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer policyId;
	private String policyHolderName;
	private String gender;
	private String birthDate;
	private String mobileNumber;
	private String city;
	private String nomineeName;
	private String relationship;

}

package com.hcl.einsurance.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BuyResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;

}

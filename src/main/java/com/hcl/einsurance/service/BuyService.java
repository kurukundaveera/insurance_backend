package com.hcl.einsurance.service;

import com.hcl.einsurance.dto.BuyRequestDto;
import com.hcl.einsurance.dto.BuyResponseDto;

public interface BuyService {
	
	BuyResponseDto buyPolicy(BuyRequestDto buyRequestDto);

}

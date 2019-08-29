package com.hcl.einsurance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hcl.einsurance.dto.PolicyDetailsResponseDto;

@Service
public interface PolicyDetailsService {
	
	public List<PolicyDetailsResponseDto> getPolicyDetails(Integer policyId); 

}

package com.hcl.einsurance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hcl.einsurance.dto.PolicyResponseDto;
@Service
public interface PolicyService {
	public List<PolicyResponseDto> getPolicyDetails(String all);

}

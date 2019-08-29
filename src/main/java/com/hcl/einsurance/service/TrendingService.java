package com.hcl.einsurance.service;

import java.util.List;

import com.hcl.einsurance.dto.TrendResponseDto;

public interface TrendingService {

	List<TrendResponseDto> getAllTrendingPolicies();

}

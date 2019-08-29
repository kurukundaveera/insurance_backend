package com.hcl.einsurance.service;

import java.util.List;

import com.hcl.einsurance.dto.TrendingResponseDto;

public interface TrendingService {

	List<TrendingResponseDto> getAllTrendingPolicies();

}

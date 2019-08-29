package com.hcl.einsurance.service;

import java.util.List;

import com.hcl.einsurance.dto.TrendResponseDto;

public interface TopTrendingService {

	List<TrendResponseDto> getTopTrendingPolicies();

}

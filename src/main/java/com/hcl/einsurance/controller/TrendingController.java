package com.hcl.einsurance.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.einsurance.dto.TrendingResponseDto;
import com.hcl.einsurance.service.TrendingService;

/**
 * @author DeepikaSivarajan
 *
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class TrendingController {
	private static final Logger logger = LoggerFactory.getLogger(TrendingController.class);
	@Autowired
	TrendingService trendingService;

	/**
	 * This method is intended to list trending policies based on the count
	 * 
	 * @return TrendingResponseDto which includes
	 *         policyId,policyName,count,percentage
	 */

	@GetMapping("/trending")
	public ResponseEntity<List<TrendingResponseDto>> getAllTrendingPolicies() {
		logger.info("trending policies");
		List<TrendingResponseDto> response = trendingService.getAllTrendingPolicies();
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}

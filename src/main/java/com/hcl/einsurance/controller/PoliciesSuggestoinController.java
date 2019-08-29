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

import com.hcl.einsurance.dto.PolicyResponseDto;
import com.hcl.einsurance.service.PoliciesSuggestionServiceImpl;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class PoliciesSuggestoinController {
	
	private static final Logger logger = LoggerFactory.getLogger(PoliciesSuggestoinController.class);
	
	@Autowired
	PoliciesSuggestionServiceImpl policiesSuggestionServiceImpl;
	
	@GetMapping("/api")
	public ResponseEntity<List<PolicyResponseDto>> getSuggestionPolicies()
	{
		logger.info("in getSuggestionPolicies");
		List<PolicyResponseDto> response = policiesSuggestionServiceImpl.getSuggestionPolicies();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}

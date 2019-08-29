package com.hcl.einsurance.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.einsurance.dto.PolicyResponseDto;
import com.hcl.einsurance.service.PolicyService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class PolicyController {
	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);
	@Autowired 
	PolicyService policyService;
	@GetMapping("/analysys/{all}")
	public ResponseEntity<List<PolicyResponseDto>> getAllPolicyDetails(@PathVariable String all){
		logger.info("inside getAllPolicyDetails method..");
		List<PolicyResponseDto> policyList = policyService.getPolicyDetails(all);
		return new ResponseEntity<>(policyList, HttpStatus.OK);
		
	}
}

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
import com.hcl.einsurance.dto.PolicyDetailsResponseDto;
import com.hcl.einsurance.service.PolicyDetailsServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * @author Gurpreet Singh.
 *  This is the controller class for Policy Details by
 *         policyId.
 *
 */

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class PolicyDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

	@Autowired
	PolicyDetailsServiceImpl policyDeatilsServiceImpl;

	/**
	 * @param Integer policyId is the input parameter
	 * @return List of PolicyDetailsResponseDto which includes two objects
	 *         PolicyTermConditionDto and SalientFeaturesDto with status code.
	 *
	 */
	@ApiOperation(value = "It gives the details of particular policy")
	@GetMapping("/policy/{policyId}")
	public ResponseEntity<List<PolicyDetailsResponseDto>> getPolicyDetails(@PathVariable Integer policyId) {
		logger.info("in getPolicyDetails()");
		List<PolicyDetailsResponseDto> response = policyDeatilsServiceImpl.getPolicyDetails(policyId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

package com.hcl.einsurance.service;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.einsurance.dto.PolicyResponseDto;
import com.hcl.einsurance.entity.Policies;
import com.hcl.einsurance.exception.CommonException;
import com.hcl.einsurance.repository.PolicyRepository;
import com.hcl.einsurance.util.EinsuranceConstants;

/**
 * 
 * @author Venkat Reddy
 *
 */
@Service
public class PolicyServiceImpl implements PolicyService{
@Autowired PolicyRepository policyRepository;
private static final Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);

/**
 * 
 * This method is used for fetch the list of policies
 * 
 * @param String is the request object which contains
 *                      input request type to fetch the details.
 * @return it returns PolicyResponseDto object it contains policyId, policyName, policyMinAge,policyMaxAge,policyPrice
 * 
 */

 @Override
	public List<PolicyResponseDto> getPolicyDetails(String type) {
	 logger.info("in getPolicy method");
		List<PolicyResponseDto> responseList = new ArrayList<>();
		List<Policies> policyList;
		if(type.isEmpty()) {
			throw new CommonException(EinsuranceConstants.POLICY_DETAILS_NOT_FOUND);
		}
		else if(type.equalsIgnoreCase("all")){
			policyList = policyRepository.findAll();
			policyList.stream().forEach(c -> {
				PolicyResponseDto response = new PolicyResponseDto();
				BeanUtils.copyProperties(c, response);
				responseList.add(response);
			});
		}
		
		return responseList;
 }}

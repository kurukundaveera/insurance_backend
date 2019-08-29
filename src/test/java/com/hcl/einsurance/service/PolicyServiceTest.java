package com.hcl.einsurance.service;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcl.einsurance.dto.PolicyResponseDto;
import com.hcl.einsurance.entity.Policies;
import com.hcl.einsurance.exception.CommonException;
import com.hcl.einsurance.repository.PolicyRepository;
@RunWith(MockitoJUnitRunner.class)
public class PolicyServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyServiceTest.class);
	@Mock PolicyRepository policyRepository;
	@InjectMocks PolicyServiceImpl policyServiceImpl;
	List<Policies> policyList;
	List<PolicyResponseDto> responseList;
	List<Policies> policyList2;
	List<PolicyResponseDto> responseList2;
	PolicyResponseDto policyResponseDto;
	Policies policies;
	@Before
	public void setUp() {
		policyList = new ArrayList<>();
		responseList = new ArrayList<>();
		policyList2 = new ArrayList<>();
		responseList2 = new ArrayList<>();
		
		policies = new Policies();
		policies.setPolicyId(1);
		policies.setPolicyMaxAge(56);
		policies.setPolicyMinAge(18);
		policies.setPolicyName("LIC Jeevan Anand");
		policies.setPolicySumAssured(12323);
		policies.setPolicyTerm("something");
		policyList.add(policies);
		policyList2.add(policies);
		policyResponseDto = new PolicyResponseDto();
		policyResponseDto.setPolicyId(1);
		policyResponseDto.setPolicyMaxAge(89);
		policyResponseDto.setPolicyMinAge(18);
		policyResponseDto.setPolicyName("E LIC Form");
		policyResponseDto.setPolicyPrice(23D);
		responseList.add(policyResponseDto);
		responseList2.add(policyResponseDto);
	}
@Test
public void getPolicyDetailsTest() {
	logger.info("inside the getPolicyDetailsTest method..");
	Mockito.when(policyRepository.findAll()).thenReturn(policyList);
	List<PolicyResponseDto> response = policyServiceImpl.getPolicyDetails("all");	
	Assert.assertEquals(responseList.size(), response.size());
}

@Test
public void getPolicyDetailsTest_2() {
	logger.info("inside the getPolicyDetailsTest method..");
	
	Mockito.when(policyRepository.getAllSuggestingPolicies()).thenReturn(responseList2);
	List<PolicyResponseDto> response = policyServiceImpl.getPolicyDetails("suggestions");	
	Assert.assertEquals(responseList2.size(), response.size());
}

@Test(expected = CommonException.class)
public void getPolicyDetailsTest_1() {
	logger.info("inside the getPolicyDetailsTest method..");
	 policyServiceImpl.getPolicyDetails("suggestions");	
}

@Test(expected = CommonException.class)
public void getPolicyDetailsTest_3() {
	logger.info("inside the getPolicyDetailsTest method..");
	 policyServiceImpl.getPolicyDetails("");	
}
}

package com.hcl.einsurance.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.einsurance.dto.PolicyDetailsResponseDto;
import com.hcl.einsurance.dto.PolicyTermConditionDto;
import com.hcl.einsurance.dto.SalientFeaturesDto;
import com.hcl.einsurance.entity.Policies;
import com.hcl.einsurance.entity.PolicyTermCondition;
import com.hcl.einsurance.entity.SalientFeatures;
import com.hcl.einsurance.repository.PolicyRepository;
import com.hcl.einsurance.repository.PolicyTermConditionRepository;
import com.hcl.einsurance.repository.SalientFeaturesRepository;

@RunWith(MockitoJUnitRunner.class)
public class PolicyDetailsServiceImplTest {

	@InjectMocks
	PolicyDetailsServiceImpl policyDetailsServiceImpl;
	
	@Mock
	PolicyRepository policyRepository;
	
	@Mock
	SalientFeaturesRepository salientFeaturesRepository;
	
	@Mock
	PolicyTermConditionRepository policyTermConditionRepository;
	
	PolicyDetailsResponseDto policyDetailsResponseDto;
	Policies policies;
	PolicyTermCondition policyTermCondition;
	SalientFeatures salientFeatures;
	List<Policies> listPolicies;
	List<PolicyDetailsResponseDto> listPolicyDetailsResponseDto;
	PolicyTermConditionDto policyTermConditionDto;
	SalientFeaturesDto salientFeaturesDto;
	
	public PolicyDetailsResponseDto getPolicyDetailsResponseDto()
	{
		PolicyDetailsResponseDto policyDetailsResponseDto = new PolicyDetailsResponseDto();
		policyDetailsResponseDto.setPolicyTermCondition(getPolicyTermConditionDto());
		policyDetailsResponseDto.setSalientFeatures(getSalientFeaturesDto());
		return policyDetailsResponseDto; 
	}
	
	public PolicyTermConditionDto getPolicyTermConditionDto()
	{
		PolicyTermConditionDto policyTermConditionDto = new PolicyTermConditionDto();
		policyTermConditionDto.setDescription("Single Premium Immediate Annuity Plan");
		policyTermConditionDto.setLoneFacility("Not available");
		policyTermConditionDto.setModesOfPremium("yearly");
		policyTermConditionDto.setPolicyRevival("Not available");
		policyTermConditionDto.setTaxbenefit("Premium paid is tax exempt under Section 80C ");
		return policyTermConditionDto;
	}
	
	public SalientFeaturesDto getSalientFeaturesDto()
	{
		SalientFeaturesDto salientFeaturesDto = new SalientFeaturesDto();
		salientFeaturesDto.setIncomeTaxBenefit("Although, premiums paid under this policy are exempted from tax under section 80C, but the pension received will be taxable");
		salientFeaturesDto.setMinimumSumAssured("100000");
		salientFeaturesDto.setPaymentMode("online and offline");
		salientFeaturesDto.setPolicyTerm("30-85 years");
		return salientFeaturesDto;
	}
	
	public Policies getPolicies()
	{
		Policies policies = new Policies();
		policies.setPolicyId(1);
		policies.setPolicyMaxAge(80);
		policies.setPolicyMinAge(18);
		policies.setPolicyName("LIC JeevanAkshay");
		policies.setPolicyPrice(123443D);
		policies.setPolicySumAssured(1234);
		policies.setPolicyTerm("10-35 years");
		return policies;
	}
	
	public PolicyTermCondition getPolicyTermCondition()
	{
		PolicyTermCondition policyTermCondition = new PolicyTermCondition();
		policyTermCondition.setDescription("Single Premium Immediate Annuity Plan");
		policyTermCondition.setLoneFacility("Not available");
		policyTermCondition.setModesOfPremium("yearly");
		policyTermCondition.setPolicyId(1);
		policyTermCondition.setPolicyRevival("Not available");
		policyTermCondition.setPolicyTermConditionId(1);
		policyTermCondition.setTaxbenefit("Premium paid is tax exempt under Section 80C ");
		return policyTermCondition;
	}
	
	public SalientFeatures getSalientFeatures()
	{
		SalientFeatures salientFeatures = new SalientFeatures();
		salientFeatures.setIncomeTaxBenefit("Although, premiums paid under this policy are exempted from tax under section 80C, but the pension received will be taxable");
		salientFeatures.setMinimumSumAssured("100000");
		salientFeatures.setPaymentMode("online and offline");
		salientFeatures.setPolicyId(1);
		salientFeatures.setPolicyTerm("10-35 years");
		salientFeatures.setSalientFeatureId(1);
		return salientFeatures;
	}
	
	@Before
	public void setup()
	{
		policies = getPolicies();
		listPolicies = new ArrayList<>();
		listPolicies.add(policies);
		policyDetailsResponseDto = getPolicyDetailsResponseDto();
		listPolicyDetailsResponseDto = new ArrayList<>();
		listPolicyDetailsResponseDto.add(policyDetailsResponseDto);
		policyTermCondition = getPolicyTermCondition();
		salientFeatures = getSalientFeatures();
		policyTermConditionDto = getPolicyTermConditionDto();
		salientFeaturesDto = getSalientFeaturesDto();
	}
	
	@Test
	public void testGetPolicyDetails()
	{
		Mockito.when(policyRepository.findByPolicyId(Mockito.anyInt())).thenReturn(listPolicies);
		Mockito.when(policyTermConditionRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(policyTermCondition));
		Mockito.when(salientFeaturesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(salientFeatures));
		List<PolicyDetailsResponseDto> response = policyDetailsServiceImpl.getPolicyDetails(1);
		assertEquals(policyDetailsResponseDto.getPolicyTermCondition().getDescription(), response.get(0).getPolicyTermCondition().getDescription());
	}
	
}

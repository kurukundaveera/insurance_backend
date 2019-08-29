package com.hcl.einsurance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.einsurance.dto.PolicyDetailsResponseDto;
import com.hcl.einsurance.dto.PolicyTermConditionDto;
import com.hcl.einsurance.dto.SalientFeaturesDto;
import com.hcl.einsurance.entity.Policies;
import com.hcl.einsurance.entity.PolicyTermCondition;
import com.hcl.einsurance.entity.SalientFeatures;
import com.hcl.einsurance.exception.CommonException;
import com.hcl.einsurance.repository.PolicyRepository;
import com.hcl.einsurance.repository.PolicyTermConditionRepository;
import com.hcl.einsurance.repository.SalientFeaturesRepository;
import com.hcl.einsurance.util.EinsuranceConstants;


/**
 * @author Gurpreet Singh
 *
 */
@Service
public class PolicyDetailsServiceImpl implements PolicyDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyDetailsServiceImpl.class);

	@Autowired
	PolicyRepository policyRepository;
	
	@Autowired
	SalientFeaturesRepository salientFeaturesRepository;
	
	@Autowired
	PolicyTermConditionRepository policyTermConditionRepository;
	
	/**
	 * @param Integer policyId is the input parameter
	 * @return List of PolicyDetailsResponseDto which includes two objects
	 *         PolicyTermConditionDto and SalientFeaturesDto with status code.
	 *
	 */
	@Override
	public List<PolicyDetailsResponseDto> getPolicyDetails(Integer policyId) {
		logger.info("in getPolicyDetails()");
		List<PolicyDetailsResponseDto> responseList = new ArrayList<>();
		List<Policies> policy = policyRepository.findByPolicyId(policyId);
		Optional<PolicyTermCondition> policyTerm = policyTermConditionRepository.findById(policy.get(0).getPolicyId());
		Optional<SalientFeatures> salientFeatures = salientFeaturesRepository.findById(policy.get(0).getPolicyId());
		if(policy.isEmpty())
			throw new CommonException(EinsuranceConstants.POLICY_NOT_FOUND);
		if(!policyTerm.isPresent())
			throw new CommonException(EinsuranceConstants.POLICY_TERM_CONDITION_NOT_FOUND);
		if(!salientFeatures.isPresent())
			throw new CommonException(EinsuranceConstants.SALIENT_FEATURES_NOT_FOUND);
		policy.stream().forEach(p->
		{
			PolicyDetailsResponseDto policyResponse = new PolicyDetailsResponseDto();
			SalientFeaturesDto salientDto = new SalientFeaturesDto();
			
			PolicyTermConditionDto policyTermDto = PolicyTermConditionDto.builder().description(policyTerm.get().getDescription()).loneFacility(policyTerm.get().getLoneFacility())
			.modesOfPremium(policyTerm.get().getModesOfPremium()).taxbenefit(policyTerm.get().getTaxbenefit())
			.policyRevival(policyTerm.get().getPolicyRevival()).build();
			
			salientDto.setIncomeTaxBenefit(salientFeatures.get().getIncomeTaxBenefit());
			salientDto.setMinimumSumAssured(salientFeatures.get().getMinimumSumAssured());
			salientDto.setPaymentMode(salientFeatures.get().getPaymentMode());
			salientDto.setPolicyTerm(salientFeatures.get().getPolicyTerm());
			
			policyResponse.setPolicyTermCondition(policyTermDto);
			policyResponse.setSalientFeatures(salientDto);
			
			BeanUtils.copyProperties(p, policyResponse);
			responseList.add(policyResponse);
		});
		
		return responseList;
	}

}

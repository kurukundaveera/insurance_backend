package com.hcl.einsurance.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.einsurance.dto.BuyRequestDto;
import com.hcl.einsurance.dto.BuyResponseDto;
import com.hcl.einsurance.entity.Policies;
import com.hcl.einsurance.entity.PolicyHolder;
import com.hcl.einsurance.entity.Purchase;
import com.hcl.einsurance.exception.CommonException;
import com.hcl.einsurance.repository.PolicyHolderRepository;
import com.hcl.einsurance.repository.PolicyRepository;
import com.hcl.einsurance.repository.PurchaseRepository;
import com.hcl.einsurance.util.EinsuranceConstants;

/**
 * 
 * @author HAriPriya G
 *
 */

@Service
public class BuyServiceImpl implements BuyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BuyServiceImpl.class);

	@Autowired
	PolicyRepository policyRepository;

	@Autowired
	PolicyHolderRepository policyHolderRepository;

	@Autowired
	PurchaseRepository purchaseRepository;

	/**
	 * 
	 * This method is used for buy a policy
	 * 
	 * @param buyRequestDto it is the request object which contains
	 *                      policyHolderName,gender,age,mobileNumber,city,nomineeName
	 *                      and relationsShip
	 * @return it returns BuyResponseDto object it contains message("Purchased
	 *         successfully")
	 * 
	 */

	@Override
	public BuyResponseDto buyPolicy(BuyRequestDto buyRequestDto) {
		LOGGER.info("Buy policy service impl");

		Optional<Policies> policies = policyRepository.findById(buyRequestDto.getPolicyId());
		Optional<PolicyHolder> policyHolderDb = policyHolderRepository
				.findByMobileNumber(buyRequestDto.getMobileNumber());

		if (!policies.isPresent())
			throw new CommonException(EinsuranceConstants.POLICY_NOT_FOUND);
		if (!validateCustomerName(buyRequestDto.getPolicyHolderName())) {
			throw new CommonException(EinsuranceConstants.INVALID_NAME);
		}
		if (!validMobileNumber(buyRequestDto.getMobileNumber())) {
			throw new CommonException(EinsuranceConstants.INVALID_MOBILENUMBER);
		}
		if (!validateCustomerName(buyRequestDto.getNomineeName())) {
			throw new CommonException(EinsuranceConstants.INVALID_NAME);
		}
		int age = calculateAge(buyRequestDto.getBirthDate());
		if (age < policies.get().getPolicyMinAge() || age > policies.get().getPolicyMaxAge())
			throw new CommonException(EinsuranceConstants.ERROR_AGE);

		if (policyHolderDb.isPresent()) {
			Optional<Purchase> purchaseDb = purchaseRepository.findByPolicyIdAndPolicyHolderId(
					policies.get().getPolicyId(), policyHolderDb.get().getPolicyHolderId());
			if (purchaseDb.isPresent())
				throw new CommonException(EinsuranceConstants.ERROR_POLICY_EXCEPTION);
		}

		PolicyHolder policyHolder = new PolicyHolder();
		policyHolder.setPolicyHolderName(buyRequestDto.getPolicyHolderName());
		policyHolder.setGender(buyRequestDto.getGender());
		policyHolder.setMobileNumber(buyRequestDto.getMobileNumber());
		policyHolder.setNomineeName(buyRequestDto.getNomineeName());
		policyHolder.setRelationship(buyRequestDto.getRelationship());
		policyHolder.setAge(age);
		policyHolder.setCity(buyRequestDto.getCity());
		PolicyHolder policyHolderDbs = policyHolderRepository.save(policyHolder);

		Purchase purchase = new Purchase();
		purchase.setPolicyHolderId(policyHolderDbs.getPolicyHolderId());
		purchase.setPolicyId(policies.get().getPolicyId());
		purchase.setStatus("P");
		purchaseRepository.save(purchase);

		return new BuyResponseDto("Purchased successfully");
	}

	private boolean validMobileNumber(String number) {
		Pattern p = Pattern.compile("^[0-9]{10}$");
		Matcher m = p.matcher(number);
		return (m.find() && m.group().equals(number));
	}

	private boolean validateCustomerName(String customerName) {
		String name = ("^[a-zA-Z]*$");
		return customerName.matches(name);
	}

	public int calculateAge(String birhtDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EinsuranceConstants.DATE_FORMAT);
		LocalDate localDate = LocalDate.now();
		String dateStr = formatter.format(localDate);

		LocalDate birthLocalDate = LocalDate.parse(birhtDate, formatter);

		String birhDateStr = formatter.format(birthLocalDate);

		LocalDate nowDate = LocalDate.parse(dateStr, formatter);
		LocalDate birthDate = LocalDate.parse(birhDateStr, formatter);

		return Period.between(birthDate, nowDate).getYears();
	}

}

package com.hcl.einsurance.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.einsurance.dto.BuyRequestDto;
import com.hcl.einsurance.dto.BuyResponseDto;
import com.hcl.einsurance.entity.Policies;
import com.hcl.einsurance.entity.PolicyHolder;
import com.hcl.einsurance.entity.Purchase;
import com.hcl.einsurance.exception.CommonException;
import com.hcl.einsurance.repository.PolicyHolderRepository;
import com.hcl.einsurance.repository.PolicyRepository;
import com.hcl.einsurance.repository.PurchaseRepository;

@RunWith(MockitoJUnitRunner.class)
public class BuyServiceImplTest {

	@Mock
	PolicyRepository policyRepository;

	@Mock
	PolicyHolderRepository policyHolderRepository;

	@Mock
	PurchaseRepository purchaseRepository;

	@InjectMocks
	BuyServiceImpl buyServiceImpl;

	Purchase purchase;
	Policies policies;
	PolicyHolder policyHolder;
	BuyRequestDto buyRequestDto;

	@Before
	public void setUp() {
		purchase = getPurchase();
		policies = getPolicy();
		policyHolder = getPolicyHolder();
		buyRequestDto = getBuRequestDto();
	}

	@Test
	public void buyServiceImplTest() {
		Mockito.when(policyRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(policies));
		Mockito.when(policyHolderRepository.findByMobileNumber(Mockito.anyString()))
				.thenReturn(Optional.of(policyHolder));
		Mockito.when(policyHolderRepository.save(Mockito.any())).thenReturn(policyHolder);
		Mockito.when(purchaseRepository.save(Mockito.any())).thenReturn(purchase);
		BuyResponseDto actual = buyServiceImpl.buyPolicy(buyRequestDto);
		Assert.assertEquals("Purchased successfully", actual.getMessage());

	}

	@Test(expected = CommonException.class)
	public void buyServiceImplTest_1() {
		buyRequestDto.setPolicyId(100);
		buyServiceImpl.buyPolicy(buyRequestDto);
	}

	@Test(expected = CommonException.class)
	public void buyServiceImplTest_2() {
		buyRequestDto.setPolicyHolderName("Hari priya");
		Mockito.when(policyRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(policies));
		buyServiceImpl.buyPolicy(buyRequestDto);
	}

	@Test(expected = CommonException.class)
	public void buyServiceImplTest_3() {
		buyRequestDto.setMobileNumber("234234234");
		Mockito.when(policyRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(policies));
		buyServiceImpl.buyPolicy(buyRequestDto);
	}

	@Test(expected = CommonException.class)
	public void buyServiceImplTest_4() {
		buyRequestDto.setNomineeName("priya hari");
		Mockito.when(policyRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(policies));
		buyServiceImpl.buyPolicy(buyRequestDto);
	}

	@Test(expected = CommonException.class)
	public void buyServiceImplTest_5() {
		buyRequestDto.setBirthDate("2010-08-22");
		Mockito.when(policyRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(policies));
		buyServiceImpl.buyPolicy(buyRequestDto);
	}

	@Test(expected = CommonException.class)
	public void buyServiceImplTest_6() {
		Mockito.when(policyRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(policies));
		Mockito.when(policyHolderRepository.findByMobileNumber(Mockito.anyString()))
				.thenReturn(Optional.of(policyHolder));
		Mockito.when(purchaseRepository.findByPolicyIdAndPolicyHolderId(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(Optional.of(purchase));
		buyServiceImpl.buyPolicy(buyRequestDto);
	}

	public Policies getPolicy() {
		return new Policies(1, "AAA", 15, 30, 12355.00, 1234, "10-35 years");
	}

	public PolicyHolder getPolicyHolder() {
		return new PolicyHolder(2, "BBB", "male", 20, "2342342342", "banglore", "cccc", "Mother");
	}

	public Purchase getPurchase() {
		return new Purchase(1, 1, 2, LocalDate.now(), "P");
	}

	public BuyRequestDto getBuRequestDto() {
		return new BuyRequestDto(2, policyHolder.getPolicyHolderName(), policyHolder.getGender(), "2000-03-23",
				"8908908908", policyHolder.getCity(), policyHolder.getNomineeName(), policyHolder.getNomineeName());
	}

}

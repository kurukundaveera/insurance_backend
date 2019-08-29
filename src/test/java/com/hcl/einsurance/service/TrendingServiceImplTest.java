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

import com.hcl.einsurance.dto.TrendResponseDto;
import com.hcl.einsurance.dto.TrendingResponseDto;
import com.hcl.einsurance.exception.CommonException;
import com.hcl.einsurance.repository.PurchaseRepository;

/**
 * 
 * @author DeepikaSivarajan
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TrendingServiceImplTest {
	@Mock
	PurchaseRepository purchaseRepository;
	@InjectMocks
	TrendingServiceImpl trendingServiceImpl;
	@InjectMocks
	TopTrendingServiceImpl topTrendingServiceImpl;
	List<TrendingResponseDto> trendingList;
	TrendingResponseDto trendingResponseDto;
	List<TrendResponseDto> responseList;
	TrendResponseDto trendResponseDto;

	@Before
	public void setUp() {
		trendingList = new ArrayList<>();
		trendingResponseDto = new TrendingResponseDto();
		trendingResponseDto.setPolicyId(1);
		trendingResponseDto.setCount(2l);
		trendingList.add(trendingResponseDto);
		responseList = new ArrayList<>();
		trendResponseDto = new TrendResponseDto();
		trendResponseDto.setPolicyId(trendingResponseDto.getPolicyId());
		trendResponseDto.setPercentage((trendingResponseDto.getCount() / 10d) * 100d);
		responseList.add(trendResponseDto);
	}

	@Test
	public void testGetAllTrendingPolicies() {
		Mockito.when(purchaseRepository.getAllTrendings()).thenReturn(trendingList);
		List<TrendResponseDto> response = trendingServiceImpl.getAllTrendingPolicies();
		Assert.assertEquals(trendingList.get(0).getPolicyId(), response.get(0).getPolicyId());

	}

	@Test(expected = CommonException.class)
	public void testGetAllTrendingPolicies_1() {
		trendingServiceImpl.getAllTrendingPolicies();
	}

	@Test
	public void testGetTopTrendingPolicies() {
		Mockito.when(purchaseRepository.getTopTrendings(Mockito.any())).thenReturn(trendingList);
		List<TrendResponseDto> response = topTrendingServiceImpl.getTopTrendingPolicies();
		Assert.assertEquals(trendingList.get(0).getPolicyId(), response.get(0).getPolicyId());
	}
	@Test(expected = CommonException.class)
	public void testGetTopTrendingPolicies_1() {
		topTrendingServiceImpl.getTopTrendingPolicies();

	}

	
}

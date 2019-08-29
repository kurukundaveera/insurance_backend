package com.hcl.einsurance.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.einsurance.dto.BuyRequestDto;
import com.hcl.einsurance.service.BuyService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class BuyControllerTest {
	

@InjectMocks BuyController buyController;

@Mock BuyService buyService;
BuyRequestDto buyRequestDto;
MockMvc mockMvc;
private static final Logger logger = LoggerFactory.getLogger(BuyControllerTest.class);
@Before
public void setUp() {
	mockMvc = MockMvcBuilders.standaloneSetup(buyController).build();
	buyRequestDto = getBuyRequestDto();
}
private BuyRequestDto getBuyRequestDto() {
	return new BuyRequestDto();
}
@Test
public void buyPolicyTest() throws Exception{
	logger.info("inside the buyPolicyTest method");	
	mockMvc.perform(MockMvcRequestBuilders.post("/api/buy").contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON).content(asJsonString(buyRequestDto)))
			.andExpect(status().isCreated());
}
public static String asJsonString(final Object obj) {
	try {
		return new ObjectMapper().writeValueAsString(obj);
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
}
}

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

import com.hcl.einsurance.repository.PolicyRepository;
import com.hcl.einsurance.service.PolicyDetailsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class PolicyDetailsControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(PolicyDetailsControllerTest.class);
	
	 MockMvc mockMvc;
	
	@InjectMocks
	PolicyDetailsController policyDetailsController;
	
	@Mock
	PolicyRepository policyRepository;
	
	@Mock
	PolicyDetailsServiceImpl policyDetailsServiceImpl;
	
	@Before
	public void setup()
	{
		mockMvc = MockMvcBuilders.standaloneSetup(policyDetailsController).build();
	}
	
	@Test
	public void testgetPolicyDetails() throws Exception
	{
		logger.info("in testgetPolicyDetails()");
		mockMvc.perform(MockMvcRequestBuilders.get("/api/policy/{policyId}",1)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	

}

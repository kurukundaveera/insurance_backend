package com.hcl.einsurance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.einsurance.dto.BuyRequestDto;
import com.hcl.einsurance.dto.BuyResponseDto;
import com.hcl.einsurance.service.BuyService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class BuyController {
	public static final Logger logger = LoggerFactory.getLogger(BuyController.class);

	@Autowired
	BuyService buyService;
	@PostMapping("/buy")
	public ResponseEntity<BuyResponseDto> buyPolicy(@RequestBody BuyRequestDto buyRequestDto) {
		logger.info("Buy policy  controller");
		return new ResponseEntity<>(buyService.buyPolicy(buyRequestDto), HttpStatus.CREATED);
	}

}

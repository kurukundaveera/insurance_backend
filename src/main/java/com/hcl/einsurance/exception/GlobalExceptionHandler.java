package com.hcl.einsurance.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(ProductsNotFoundException.class)
//	public ResponseEntity<ErrorResponse> productsException(Exception e) {
//		return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()),
//				HttpStatus.NOT_FOUND);
//	}

}

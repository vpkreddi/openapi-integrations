package com.demo.openapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.openapi.domain.ValidatePhoneNumberResponseDTO;
import com.demo.openapi.services.OpenPiService;

@RestController
public class OpenAPIController {

	@Autowired
	private OpenPiService service;

	@PostMapping("/validate/phoneNumbers")
	public ValidatePhoneNumberResponseDTO validatePhoneNumber(String[] numbers) {
		return service.validate(numbers);
	}
}

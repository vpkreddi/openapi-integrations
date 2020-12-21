package com.demo.openapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.openapi.domain.ValidatePhoneNumberResponseDTO;
import com.demo.openapi.services.OpenPiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api
public class OpenAPIController {

	@Autowired
	private OpenPiService service;

	@ApiOperation(value = "validate phone number")
	@PostMapping(value="/validate/phoneNumbers")
	public ValidatePhoneNumberResponseDTO validatePhoneNumber(
			@ApiParam(
					name =  "input",
				    type = "String",
				    value = "comma separated strings",
				    example = "91-1234567890,91-234455") @RequestParam(defaultValue = "91-1234567890,91-234455") String[] numbers) {
		return service.validate(numbers);
	}
}

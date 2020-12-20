package com.demo.openapi.rest.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.openapi.domain.CountryCode;

@FeignClient(name="openapi", url="https://restcountries.eu/rest/v2/all")
public interface OpenAPIClient {

	@GetMapping("?fields=name;callingCodes;alpha2Code")
	public List<CountryCode> getCountryCodes() ;
}

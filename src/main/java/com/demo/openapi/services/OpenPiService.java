package com.demo.openapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.openapi.domain.CountryCode;
import com.demo.openapi.domain.ValidatePhoneNumberResponseDTO;
import com.demo.openapi.domain.ValidateResult;
import com.demo.openapi.rest.client.OpenAPIClient;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OpenPiService {

	@Autowired
	private OpenAPIClient restClient;

	public ValidatePhoneNumberResponseDTO validate(String[] number) {
		List<CountryCode> resDTO = restClient.getCountryCodes();
		List<ValidateResult> result = new ArrayList<>(0);
		ValidatePhoneNumberResponseDTO response = new ValidatePhoneNumberResponseDTO();

		List<String> countryCodes = resDTO.stream().filter(data -> {
			return !(data.getCallingCodes().get(0).isEmpty() || data.getCallingCodes().get(0).contains(" "));
			}).map(data -> data.getCallingCodes().get(0))
				.collect(Collectors.toList());
		List<Integer> intCodes = countryCodes.stream().map(data -> Integer.valueOf(data)).collect(Collectors.toList());
		Stream.of(number).forEach(numberString -> response.setResult(checkNumber(intCodes,result, resDTO,numberString)));
		return response;
	}

	private List<ValidateResult> checkNumber(List<Integer> codes, List<ValidateResult> result ,List<CountryCode> resDTO, String number) {
		//int mobileIndex = number.length()-10;
		String[] split = number.split("-");
		if(split.length>2) {
			throw new RuntimeException("invalid number string");
		}
		String code = split[0];
		String mobile = split[1];
		AtomicReference<CountryCode>  matchedCode = new AtomicReference<>(null);
		resDTO.forEach(obj -> {if(obj.getCallingCodes().get(0).equals(code)) {
			matchedCode.set(obj);
			}
		});
		if (codes.contains(Integer.valueOf(code)) && isValid(mobile,matchedCode.get().getAlpha2Code()) ) {
			result.add(new ValidateResult(number, true));
		} else {
			result.add(new ValidateResult(number, false));
		}
		return result;
	}

	private boolean isValid(String mobile, String code) {
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		Boolean isValid =false;
		try {
		  PhoneNumber swissNumberProto = phoneUtil.parse(mobile, code);
		  isValid = phoneUtil.isValidNumber(swissNumberProto);
		} catch (NumberParseException e) {
		  log.error("NumberParseException was thrown: " + e.toString());
		  return isValid;
		}
		return isValid;
	}
}

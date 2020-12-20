package com.demo.openapi.domain;

import java.util.Comparator;

public class CountryCodeComparator implements Comparator<CountryCode> {

	@Override
	public int compare(CountryCode code0, CountryCode code1) {
		return code0.getCallingCodes().get(0).compareTo(code1.getCallingCodes().get(0));
	}

}

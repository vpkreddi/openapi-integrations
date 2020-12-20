package com.demo.openapi.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryCode {
	private String name;
	private List<String> callingCodes;
	private String alpha2Code;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((callingCodes == null) ? 0 : callingCodes.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryCode other = (CountryCode) obj;
		if (callingCodes == null) {
			if (other.callingCodes != null)
				return false;
		} else if (!callingCodes.equals(other.callingCodes))
			return false;
		return true;
	}
	
	
}

package com.example.secureREST;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class RoleConverter implements org.springframework.core.convert.converter.Converter<Jwt, Collection<GrantedAuthority>>{

	@Override
	public Collection<GrantedAuthority> convert(Jwt jwt) {
		Map<String, Object> realmAccess = jwt.getClaimAsMap("real_access");
		if(null == realmAccess || realmAccess.isEmpty()) {
			return Collections.emptyList();
		}
		
		@SuppressWarnings("unchecked")
		Collection<GrantedAuthority> returnValue = ((List<String>) realmAccess.get("role")).stream()
																								.map(roleName->"ROLE_"+roleName)
																								.map(SimpleGrantedAuthority::new)
																								.collect(Collectors.toList());
		return returnValue;
	}

}

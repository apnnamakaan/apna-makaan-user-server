package com.nvc.user.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nvc.user.responses.VerifyResponse;

@Component
public class Authorization {
	
	public VerifyResponse verifyToken(String token) {
		String uri = "http://localhost:5000/auth/verify";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("authorization", token);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<VerifyResponse> response = restTemplate.exchange(uri,  HttpMethod.POST, request, VerifyResponse.class);
		return response.getBody();
	}
	
}

package com.nvc.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nvc.user.exceptions.ResourceNotFoundException;
import com.nvc.user.exceptions.UnauthorizeException;
import com.nvc.user.models.User;
import com.nvc.user.repositorys.UserRepository;
import com.nvc.user.responses.VerifyResponse;
import com.nvc.user.utils.Authorization;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Authorization authorization;
	
	@Override
	public User getUserByEmail(String email) {
		User user = this.userRepository.getUserByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("user not found"));
		return user;
	}
	
//	
//	@Override
//	public VerifyResponse verifyToken(String token) {
//		String uri = "http://localhost:5000/auth/verify";
//		
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("authorization", token);
//		
//		HttpEntity<String> request = new HttpEntity<String>(headers);
//		ResponseEntity<VerifyResponse> response = restTemplate.exchange(uri,  HttpMethod.POST, request, VerifyResponse.class);
//		return response.getBody();
//	}


	@Override
	public void updateUserByEmail(String email, User user,String token) {
		
		VerifyResponse response =  this.authorization.verifyToken(token);
		
		if(!(response.getEmail().equals(email) || response.isAdmin())) throw new UnauthorizeException("you are not authorized");
		
		
		User savedUser = this.userRepository.getUserByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("user not found"));

		if (user.getName() != null && user.getName() != "")
			savedUser.setName(user.getName());
		
		if (user.getPhone() != 0)
			savedUser.setPhone(user.getPhone());

		this.userRepository.save(savedUser);

	}

	@Override
	public void deletUserByEmail(String email,String token) {
		
		VerifyResponse response =  this.authorization.verifyToken(token);
		
		if(!(response.getEmail().equals(email) || response.isAdmin())) throw new UnauthorizeException("you are not authorized");
		

		User savedUser = this.userRepository.getUserByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("user not found"));
		
		this.userRepository.delete(savedUser);	
	}

	@Override
	public User getUserByToken(String token) {
		VerifyResponse response =   this.authorization.verifyToken(token);
		return this.getUserByEmail(response.getEmail());
	}



}

package com.nvc.user.services;

import com.nvc.user.models.User;
import com.nvc.user.responses.VerifyResponse;

public interface UserService {
	
	//VerifyResponse verifyToken(String token);
	
	User getUserByEmail(String email);
	User getUserByToken(String token);
	void updateUserByEmail(String email,User user,String token);
	void deletUserByEmail(String email,String token);
}

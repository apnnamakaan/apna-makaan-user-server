package com.nvc.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nvc.user.models.User;
import com.nvc.user.responses.ApiResponse;
import com.nvc.user.services.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<User> getUserByEmail(@RequestParam(name = "email") String email) {
		
		User user = this.userService.getUserByEmail(email);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping("/token/")
	public ResponseEntity<User> getUserByToken(@RequestHeader("authorization") String token) {
		
		User user = this.userService.getUserByToken(token);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	

	@PutMapping("/")
	public ResponseEntity<ApiResponse> updateUserByEmail(@RequestParam(name = "email") String email ,@RequestBody User user, @RequestHeader("authorization") String token) {

		this.userService.updateUserByEmail(email,user,token);
		return new ResponseEntity<>(new ApiResponse("true","update successfuly"),HttpStatus.OK);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<ApiResponse> deleteUserByEmail(@RequestParam(name = "email") String email, @RequestHeader("authorization") String token) {
		
		this.userService.deletUserByEmail(email,token);
		return new ResponseEntity<>(new ApiResponse("true","delete successfuly"),HttpStatus.OK);
	}
	

}

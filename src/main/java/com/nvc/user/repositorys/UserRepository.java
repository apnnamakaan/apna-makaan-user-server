package com.nvc.user.repositorys;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nvc.user.models.User;

@Repository
public interface  UserRepository  extends MongoRepository<User, String >{
	
	@Query("{'email' : ?0}")   
	Optional<User> getUserByEmail(String email);
		
	
}
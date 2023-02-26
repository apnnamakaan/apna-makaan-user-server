package com.nvc.user.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection ="users")
public class User {
	
    @MongoId(value = FieldType.OBJECT_ID)
	private String id;
    
	private String name;
	
	@Indexed(unique = true) 
	private String email;
	@JsonIgnore
	private String password;
	private long phone;
	
	
	@JsonIgnore
	private boolean admin;
}

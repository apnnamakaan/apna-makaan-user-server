package com.nvc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class ApnaMakaanUserServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApnaMakaanUserServerApplication.class, args);
	}

}

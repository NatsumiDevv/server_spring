package com.devPn.socialmedias;

import com.devPn.socialmedias.model.response.UserFollowingResponse;
import com.devPn.socialmedias.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SocialmediasApplication {



	public static void main(String[] args) {
		SpringApplication.run(SocialmediasApplication.class, args);

	}



}

package com.management.pg.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AppApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder encoder;
	
	public static void main(String[] args)  {
		SpringApplication.run(AppApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		String encodedPassword = encoder.encode("123");
        System.out.println("Encoded password: " + encodedPassword);
	}
}
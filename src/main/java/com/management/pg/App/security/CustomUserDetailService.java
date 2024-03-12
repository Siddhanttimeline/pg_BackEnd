package com.management.pg.App.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.management.pg.App.entity.Student;
import com.management.pg.App.repo.StudentRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
    private static final Logger log = LogManager.getLogger(CustomUserDetailService.class);


    @Autowired
    private StudentRepo studentRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Load user from database by username
    	log.info("----------------------------------------------------");
    	log.info("Inside loadUserByUsername - CustomUserDetailService");
        Student student = this.studentRepo.findByEmail(email);
        log.info("Student : "+student.toString());
        // Map Student entity to UserDetails implementation
        return org.springframework.security.core.userdetails.User.builder()
                .username(student.getEmail())
                .password(student.getPassword())
                // Add roles if applicable
                //.authorities(student.getRoles())
                .build();
    }
}

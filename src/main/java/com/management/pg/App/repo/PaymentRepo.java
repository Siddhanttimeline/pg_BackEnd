package com.management.pg.App.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.pg.App.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer>{
	
	@Query("SELECT p FROM Payment p WHERE p.student.aadharCardNumber = :aadharCard")
    List<Payment> findByAadharCard(@Param("aadharCard") String aadharCard);	
}

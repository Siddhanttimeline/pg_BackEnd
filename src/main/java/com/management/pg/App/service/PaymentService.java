package com.management.pg.App.service;

import java.util.List;

import com.management.pg.App.entity.Student;
import com.management.pg.App.payload.PaymentDTO;
import com.management.pg.App.payload.StudentDTO;
import com.razorpay.Order;
import com.razorpay.RazorpayException;

public interface PaymentService {
	
	PaymentDTO createPayment(PaymentDTO paymentdto, String aadharCard);
	
	List<PaymentDTO> getAllPayments(String aadharCard);
	
    String hasPaidEnoughForCurrentMonth(StudentDTO student);
      
}

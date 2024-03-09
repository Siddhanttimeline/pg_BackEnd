package com.management.pg.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.pg.App.payload.PaymentDTO;
import com.management.pg.App.service.PaymentService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
	
	@Autowired
	private PaymentService paymnetService;
	
	@PostMapping("/payment/{aadharCard}")
	public ResponseEntity<PaymentDTO> pay(@RequestBody PaymentDTO paymentdto, @PathVariable String aadharCard){
		PaymentDTO paid = paymnetService.createPayment(paymentdto,aadharCard);
		return new ResponseEntity<PaymentDTO>(paid, HttpStatus.CREATED);
	}
	
	@GetMapping("/payments/{aadharCard}")
	public ResponseEntity<List<PaymentDTO>> getPaymentHistory(@PathVariable String aadharCard){
		List<PaymentDTO> paid = paymnetService.getAllPayments(aadharCard);
		return new ResponseEntity<List<PaymentDTO>>(paid, HttpStatus.CREATED);
	}

}

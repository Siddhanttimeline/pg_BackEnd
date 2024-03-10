package com.management.pg.App.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.pg.App.entity.Payment;
import com.management.pg.App.entity.Room;
import com.management.pg.App.entity.Student;
import com.management.pg.App.payload.PaymentDTO;
import com.management.pg.App.payload.StudentDTO;
import com.management.pg.App.repo.PaymentRepo;
import com.management.pg.App.repo.StudentRepo;
import com.management.pg.App.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PaymentRepo paymentrepo;
	
	@Autowired
	private StudentRepo studentrepo;
	
	@Override
	public PaymentDTO createPayment(PaymentDTO paymentdto, String aadharCard) {
		Student student = studentrepo.findByAadharCardNumber(aadharCard);
		Payment payment = modelMapper.map(paymentdto, Payment.class);
		payment.setStudent(student);
		payment.setPaymentStatus(true);
		Payment savedPayment = paymentrepo.save(payment);
		return modelMapper.map(savedPayment, PaymentDTO.class);
	}

	@Override
	public List<PaymentDTO> getAllPayments(String aadharCard) {
		List<Payment> list = paymentrepo.findByAadharCard(aadharCard);
		List<PaymentDTO> listOfDTOs = list.stream()
											.map((li) -> modelMapper.map(li, PaymentDTO.class))
											.collect(Collectors.toList());
		return listOfDTOs;
	}

	@Override
	public String hasPaidEnoughForCurrentMonth(StudentDTO student) {
		LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();		
        
        List<Payment> payments = student.getPayments();
        if(payments == null) return "";
        
     // Filter payments for the current month and year
        List<Payment> paymentsForCurrentMonth = student.getPayments().stream()
                .filter(payment -> payment.getMonth() == currentMonth && payment.getYear() == currentYear)
                .collect(Collectors.toList());
        
     // Calculate total payment amount for the current month
        double totalPaymentForCurrentMonth = paymentsForCurrentMonth.stream()
                .mapToDouble(Payment::getAmount)
                .sum();

        // Fetch the room associated with the student
        Room room = student.getRoom();
        
        // Compare total payment amount with the price of the room
        return totalPaymentForCurrentMonth >= room.getPrice() ? "Paid" : "Unpaid";
     }
	
}

package com.management.pg.App.payload;

import java.time.LocalDate;
import java.util.List;

import com.management.pg.App.entity.Payment;
import com.management.pg.App.entity.Room;

import lombok.Data;

@Data
public class StudentDTO {
	
	private int id;
	
	private String name;
	
	private String email;
	
	private String address;
	
	private String phoneNumber;
	
	private String aadharCardNumber;
	
	private String profileImagePath;
	
	private String aadharCardImagePath;

    private Room room;
    
    private List<Payment> payments;
    
    private LocalDate dateOfJoining;
    
    private String currentMonthPayment;
}

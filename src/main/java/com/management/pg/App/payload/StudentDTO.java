package com.management.pg.App.payload;

import java.time.LocalDate;
import java.util.List;


import com.management.pg.App.entity.Payment;
import com.management.pg.App.entity.Room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDTO {
	
	private int id;
	
    @NotBlank(message = "Name is mandatory")
    @NotEmpty(message = "Name is mandatory")
	private String name;
	
	private String email;
	
	private String address;
	
    @NotBlank(message = "Phone number is mandatory")
    @NotEmpty(message = "Phone number is mandatory")
	private String phoneNumber;
	
    @NotEmpty(message = "Aadhar card number is mandatory")
    @NotBlank(message = "Aadhar card number is mandatory")
	private String aadharCardNumber;
	
	private String profileImagePath;
	
	private String aadharCardImagePath;

	@NotNull(message = "Room is mandatory")
    private Room room;
    
    private List<Payment> payments;
    
    private String dateOfJoining;
    
    private String currentMonthPayment;
}

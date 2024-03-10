package com.management.pg.App.entity;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "student",  indexes = {
	    @Index(name = "aadhar_card_number", columnList = "aadharCardNumber")
	})
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @Column(name = "name", nullable = false)
	private String name;
	
    @Column(name = "email")
	private String email;
	
    @Column(name = "address")
	private String address;
	
    @Column(name = "phone_number",nullable = false)
	private String phoneNumber;
	
    @Column(name = "aadhar_card_number", unique = true, nullable = false)
	private String aadharCardNumber;
    
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "room_number", referencedColumnName = "room_number")
    private Room room;
	
    @Column(name = "profile_image_path")
	private String profileImagePath;
	
    @Column(name = "aadhar_card_image_path")
	private String aadharCardImagePath;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Payment> payments;
    
    @Column(name = "date_of_joining")
    private String dateOfJoining;

}

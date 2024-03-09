package com.management.pg.App.entity;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.Index;

import lombok.Data;

@Entity
@Data
@Table(name = "rooms", indexes = {
	    @Index(name = "room_number_index", columnList = "roomNumber")
	})public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int roomNumber;
		
	private int floorNumber;
	
	private double  price;
}

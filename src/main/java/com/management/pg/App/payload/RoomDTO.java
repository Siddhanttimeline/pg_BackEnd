package com.management.pg.App.payload;

import lombok.Data;

@Data
public class RoomDTO {
	
	private int id;
	
	private int roomNumber;
		
	private int floorNumber;
	
	private double  price;

}

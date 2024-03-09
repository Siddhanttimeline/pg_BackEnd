package com.management.pg.App.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.management.pg.App.service.RoomService;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping("/getAllRooms")
	public ResponseEntity<List<Integer>> getAllRooms(){
		List<Integer> list = roomService.getAllRooms();
		return new ResponseEntity<List<Integer>>(list, HttpStatus.OK);
	}

}

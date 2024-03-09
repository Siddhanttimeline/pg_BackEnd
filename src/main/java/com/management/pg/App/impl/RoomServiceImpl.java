package com.management.pg.App.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.pg.App.entity.Room;
import com.management.pg.App.payload.RoomDTO;
import com.management.pg.App.repo.RoomRepo;
import com.management.pg.App.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public RoomDTO saveRoom(RoomDTO room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAllRooms() {
		List<Room> list = roomRepo.findAll();
		
		List<Integer> roomNumbers = new ArrayList<>();
        for (Room room : list) {
        	roomNumbers.add(room.getRoomNumber());
        }
    
		return roomNumbers;
	}

	@Override
	public RoomDTO getRoomById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRoomById(int id) {
		// TODO Auto-generated method stub

	}

}

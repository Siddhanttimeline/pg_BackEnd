package com.management.pg.App.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.management.pg.App.payload.RoomDTO;

@Service
public interface RoomService {
	
	RoomDTO saveRoom(RoomDTO room);
	
    List<Integer> getAllRooms();
    
    RoomDTO getRoomById(int id);
    
    void deleteRoomById(int id);
}

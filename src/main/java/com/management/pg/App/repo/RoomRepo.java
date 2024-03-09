package com.management.pg.App.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.pg.App.entity.Room;

public interface RoomRepo extends JpaRepository<Room, Integer>{
    Room findByRoomNumber(int roomNumber);
}

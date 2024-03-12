package com.management.pg.App.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.pg.App.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{
	
	List<Student> findByRoomRoomNumber(Integer roomNumber);

	List<Student> findByRoomFloorNumber(Integer floorNumber);
	
	Student findByAadharCardNumber(String aadharCard);
	
	Student findByEmail(String email);
}

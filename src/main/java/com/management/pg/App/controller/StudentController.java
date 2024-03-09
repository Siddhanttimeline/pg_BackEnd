package com.management.pg.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.pg.App.payload.StudentDTO;
import com.management.pg.App.payload.StudentDTOWithImage;
import com.management.pg.App.service.StudentService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@PostMapping("/student/admin/save")
	public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentdto){
		StudentDTO createdStudent = service.saveStudent(studentdto);
		return new ResponseEntity<StudentDTO>(createdStudent, HttpStatus.CREATED);
	}
	
	@PostMapping("/student/user/save")
	public ResponseEntity<StudentDTO> addStudentWithImage(
			@RequestParam("profileImage") MultipartFile profilePicture,
			@RequestParam("aadharCardImage") MultipartFile AadharCardPicture,
			@RequestParam("studentDTO") String studentDTOJson) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    StudentDTO studentDTO = null;
	    try {
	        studentDTO = objectMapper.readValue(studentDTOJson, StudentDTO.class);
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	    }
	    StudentDTOWithImage studentDTOWithImage = new StudentDTOWithImage(studentDTO, profilePicture, AadharCardPicture);
	    StudentDTO savedStudent = service.saveStudentWithImage(studentDTOWithImage);
	    return new ResponseEntity<StudentDTO>(savedStudent, HttpStatus.CREATED);
	}
	
	@PostMapping("/student/update/{studentID}")
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentdto, @PathVariable Integer studentID){
		StudentDTO updatedStudent = service.updateStudent(studentID, studentdto);
		return new ResponseEntity<StudentDTO>(updatedStudent, HttpStatus.CREATED);
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentDTO>> fetchAllStudent(){
		List<StudentDTO> listOfstudent = service.getAllStudents();
		return new ResponseEntity<List<StudentDTO> >(listOfstudent, HttpStatus.OK);
	}
	
	@GetMapping("/student/{studentID}")
	public ResponseEntity<StudentDTO> fetchByStudentID(@PathVariable Integer studentID){
		StudentDTO listOfstudent = service.getStudentById(studentID);
		return new ResponseEntity<StudentDTO>(listOfstudent, HttpStatus.OK);
	}
	
	@GetMapping("/student/roomNumber/{roomNumber}")
	public ResponseEntity<List<StudentDTO>> fetchStudentsByRoomNumber(@PathVariable Integer roomNumber){
		List<StudentDTO> listOfstudent = service.getStudentsByRoomId(roomNumber);
		return new ResponseEntity<List<StudentDTO> >(listOfstudent, HttpStatus.OK);
	}
	
	@GetMapping("/student/notPaidStudents")
	public ResponseEntity<List<StudentDTO>> fetchStudentWhoHasNotDonePayment(){
		List<StudentDTO> result = service.getStudentsPaymentStatus();
		return new ResponseEntity<List<StudentDTO> >(result, HttpStatus.OK);
	}
	
	@GetMapping("/student/floorNumber/{floorNumber}")
	public ResponseEntity<List<StudentDTO>> fetchStudentsByFloorNumber(@PathVariable Integer floorNumber){
		List<StudentDTO> listOfstudent = service.getStudentsByFloorNumber(floorNumber);
		return new ResponseEntity<List<StudentDTO> >(listOfstudent, HttpStatus.OK);
	}

}

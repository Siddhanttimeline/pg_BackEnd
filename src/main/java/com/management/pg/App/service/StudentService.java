package com.management.pg.App.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.management.pg.App.payload.StudentDTO;
import com.management.pg.App.payload.StudentDTOWithImage;

public interface StudentService {

	StudentDTO saveStudent(StudentDTO student);
	
	StudentDTO saveStudentWithImage(StudentDTOWithImage studentdto);
	
	StudentDTO updateStudent(int studentId, StudentDTO student);
    
    List<StudentDTO> getAllStudents();
    
    StudentDTO getStudentById(int id);
    
    void deleteStudentById(int id);
    
    List<StudentDTO> getStudentsByRoomId(Integer roomId);
    
    List<StudentDTO> getStudentsByFloorNumber(Integer roomId);
    
    List<StudentDTO> getStudentsPaymentStatus();

}

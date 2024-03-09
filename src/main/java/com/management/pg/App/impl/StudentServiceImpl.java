package com.management.pg.App.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.management.pg.App.entity.Room;
import com.management.pg.App.entity.Student;
import com.management.pg.App.payload.StudentDTO;
import com.management.pg.App.payload.StudentDTOWithImage;
import com.management.pg.App.repo.RoomRepo;
import com.management.pg.App.repo.StudentRepo;
import com.management.pg.App.service.FileService;
import com.management.pg.App.service.PaymentService;
import com.management.pg.App.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
    @Autowired
    private FileService fileService;
    
    @Autowired
    private PaymentService paymentService;
    
    @Value("${project.image}")
    private String path;
	
	@Override
	public StudentDTO saveStudent(StudentDTO studentdto) {
		Student student = modelMapper.map(studentdto, Student.class);
        Room room = roomRepo.findByRoomNumber(student.getRoom().getRoomNumber());
                                  
        student.setRoom(room);        
    	student.setProfileImagePath(null);
		Student savedStudent = studentRepo.save(student);
		return modelMapper.map(savedStudent, StudentDTO.class);	
	}

	@Override
	public List<StudentDTO> getAllStudents() {
		List<Student> studentsList = studentRepo.findAll();
		List<StudentDTO> listDTOs = studentsList.stream()
									.map((li) -> modelMapper.map(li, StudentDTO.class))
									.collect(Collectors.toList());
		listDTOs.forEach(student -> {
            String hasPaid = paymentService.hasPaidEnoughForCurrentMonth(student);
            student.setCurrentMonthPayment(hasPaid);
        });
		return listDTOs;
	}

	@Override
	public StudentDTO getStudentById(int id) {
		Student student = studentRepo.findById(id).orElseThrow();
		StudentDTO studentdto = modelMapper.map(student, StudentDTO.class);
	
        String hasPaid = paymentService.hasPaidEnoughForCurrentMonth(studentdto);
        studentdto.setCurrentMonthPayment(hasPaid);
		return studentdto;
	}

	@Override
	public List<StudentDTO> getStudentsByRoomId(Integer roomId) {
		List<Student> listOfStudents = studentRepo.findByRoomRoomNumber(roomId);
		List<StudentDTO> list = listOfStudents.stream()
								.map((li) -> modelMapper.map(li, StudentDTO.class))
								.collect(Collectors.toList());
		list.forEach(student -> {
            String hasPaid = paymentService.hasPaidEnoughForCurrentMonth(student);
            student.setCurrentMonthPayment(hasPaid);
        });
		
		return list;
	}

	@Override
	public void deleteStudentById(int id) {
		Student student = studentRepo.findById(id).orElseThrow();
		studentRepo.delete(student);
	}

	@Override
	public List<StudentDTO> getStudentsByFloorNumber(Integer floorNumber) {
		List<Student> listOfStudents = studentRepo.findByRoomFloorNumber(floorNumber);
		List<StudentDTO> listOfStudentsDTO = listOfStudents.stream()
				.map((li) -> modelMapper.map(li, StudentDTO.class))
				.collect(Collectors.toList());
		listOfStudentsDTO.forEach(student -> {
            String hasPaid = paymentService.hasPaidEnoughForCurrentMonth(student);
            student.setCurrentMonthPayment(hasPaid);
        });
		return listOfStudentsDTO;
	}

	@Override
	public StudentDTO updateStudent(int studentId, StudentDTO studentdto) {
		Student student = studentRepo.findById(studentId).orElseThrow();
		System.out.println("Inside updateStudent");
		System.out.println("studentdto : "+studentdto);
		System.out.println("student : "+student);
		student.setAddress(studentdto.getAddress());
		student.setEmail(studentdto.getAddress());
		student.setPhoneNumber(studentdto.getPhoneNumber());
		student.setAadharCardNumber(studentdto.getAadharCardNumber());
		student.setRoom(studentdto.getRoom());
		student.setProfileImagePath(studentdto.getProfileImagePath());
		System.out.println("student : "+student);

		Student savedStudent = studentRepo.save(student);
		StudentDTO savedStudentDTO = modelMapper.map(savedStudent, StudentDTO.class);
		return savedStudentDTO;
	}

	@Override
	public StudentDTO saveStudentWithImage(StudentDTOWithImage studentdtoWithImage) {
		StudentDTO studentdto = studentdtoWithImage.getStudentDTO();
	    MultipartFile profileImage = studentdtoWithImage.getProfilePicture();	
	    MultipartFile aadharCardImage = studentdtoWithImage.getProfilePicture();	

	    Student student = modelMapper.map(studentdto, Student.class);
        Room room = roomRepo.findByRoomNumber(student.getRoom().getRoomNumber());
                                  
        student.setRoom(room);
        String profilePicturefileName = null;
        String aadharCardPicturefileName = null;

        if(profileImage != null) {
        	try {
        		profilePicturefileName = fileService.uploadImage(path, profileImage);
        		aadharCardPicturefileName = fileService.uploadImage(path, aadharCardImage);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        
    	student.setProfileImagePath(profilePicturefileName);
    	student.setAadharCardImagePath(aadharCardPicturefileName);
		Student savedStudent = studentRepo.save(student);
		return modelMapper.map(savedStudent, StudentDTO.class);	
	}

	@Override
	public List<StudentDTO> getStudentsPaymentStatus() {
		List<StudentDTO> data = this.getAllStudents();
		List<StudentDTO> filteredData = data.stream()
                .filter(student -> !"Paid".equals(student.getCurrentMonthPayment()))
                .collect(Collectors.toList());
		return filteredData;
	}

}
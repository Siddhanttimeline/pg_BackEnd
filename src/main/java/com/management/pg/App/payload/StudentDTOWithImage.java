package com.management.pg.App.payload;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTOWithImage {
	private StudentDTO studentDTO;
    private MultipartFile profilePicture;
    private MultipartFile aadharCardPicture;
    
    public StudentDTOWithImage(StudentDTO studentDTO, MultipartFile profilePicture,  MultipartFile aadharCardPicture) {
        this.studentDTO = studentDTO;
        this.profilePicture = profilePicture;
        this.aadharCardPicture = aadharCardPicture;
    }
}

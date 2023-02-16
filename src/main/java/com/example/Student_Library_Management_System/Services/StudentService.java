package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public String createStudent(Student student){
        //Student from postman is already have the basic attributes set.
        //card should be auto generated when create student function is called.
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED); //Card attribute Set
        card.setStudentVariableName(student); //foreign key attribute Set

        //Lets go to the Student attribute
        student.setCard(card);

        //If their is uniDirectional mapping : We have to save both of Them.
        //But We are super advance and are using bidirectional : child will automatically be saved.

        studentRepository.save(student);
        //By the cascading effect the card entity will be automatically saved.
        return "Student and Card added.";
    }

    public String findNameByEmail(String email){
        Student student = studentRepository.findByEmail(email);
        return student.getName();
    }
    public List<Student> findByCountry(String country){
        return studentRepository.findByCountry(country);
    }

    public String updateMob(StudentUpdateMobRequestDto studentUpdateMobRequestDto){

        //Convert the DTO into entity to save better


        //First we will try to fetch the old or original data
        Student originalStudent = studentRepository.findById(studentUpdateMobRequestDto.getId()).get();

        //We will keep the other properties and only change the required parameter
        originalStudent.setMobNo(studentUpdateMobRequestDto.getMobNo());

        //Always entity being saved
        studentRepository.save(originalStudent);
        return "Student has been updated Successfully.";
    }
}

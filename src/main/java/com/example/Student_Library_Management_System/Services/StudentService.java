package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

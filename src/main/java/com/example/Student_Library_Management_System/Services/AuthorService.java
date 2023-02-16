package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String createAuthor(AuthorEntryDto authorEntryDto){

        //Importent steps is : in the params the object is of type of DTO but repository only interact with entity
        //Solution is covert the authorEntryDto --> Author

        //Create a object of type Author
        Author author = new Author();
        //Set its attribute so that we can set the correct values in DB
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setAge(authorEntryDto.getAge());

        authorRepository.save(author);
        return "Author added Successfully";
    }
}

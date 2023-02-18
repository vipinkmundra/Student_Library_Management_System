package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.DTOs.BookResponseDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public AuthorResponseDto getAuthor(int authorId){
        Author author = authorRepository.findById(authorId).get();
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();

        List<Book> boooksWritten = author.getBooksWritten();
        List<BookResponseDto> booksWrittenDto = new ArrayList<>();
        for(Book book : boooksWritten){
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setGenre(book.getGenre());
            bookResponseDto.setPages(book.getPages());
            bookResponseDto.setName(book.getName());

            booksWrittenDto.add(bookResponseDto);
        }
        authorResponseDto.setBooksWritten(booksWrittenDto);
        authorResponseDto.setAges(author.getAge());
        authorResponseDto.setCountry(author.getCountry());
        authorResponseDto.setName(author.getName());
        authorResponseDto.setRating(author.getRating());

        return authorResponseDto;
    }
}

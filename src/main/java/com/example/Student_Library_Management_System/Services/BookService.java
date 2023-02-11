package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;
    public String addBook(Book book){
        //I want to get the author entity???
        int authorId = book.getAuthor().getId();

        //Now i will fetch the author entity
        Author author = authorRepository.findById(authorId).get();
        //Do Exceptional Handling

        //Basic attribute is already set from postman

        //Setting the foriegn key attribute int the child class
        book.setAuthor(author);

        //We need to update the list of books written in the parent class
        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);
        author.setBooksWritten(currentBooksWritten);

        //Now the book is to be saved
        //Also the Author is also to be saved
        //Since the author entity is update so we need to reset or update it

        authorRepository.save(author);//data has modified

        //save function works for update as well as add also

        //Book Repo.save is not required because cascading will handle it
        return "Book added Successfully.";
    }
}

package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.Genre;
import jakarta.persistence.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int pages;
    @Enumerated(value = EnumType.STRING)
    Genre genre;

    //Book is child with respect to author
    //Setting here the foriegn key : three steps
    @ManyToOne
    @JoinColumn //Add an extra attribute authorId(parent table PK) for the foriegn key of child table
    private Author author;

    //Book is also Child wrt Card
    @ManyToOne
    @JoinColumn
    private Card card;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transactions> transactionsList = new ArrayList<>();
    private boolean issued;
    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactions(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }
}

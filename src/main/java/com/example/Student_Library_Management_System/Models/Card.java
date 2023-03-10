package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="card")
public class Card {
    //plan is to save this Card in DB.
    //Before saving set all its attribute
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     //It is autogenerated
    @CreationTimestamp //Auto timestamp the time at which the entry is created
    private Date createOn;
    @UpdateTimestamp //Sets time when on entry is updated
    private Date updatedOn;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus; //Need to generate
    @OneToOne
    @JoinColumn
    private Student studentVariableName; //This variable is used in parent class while doing the Bi-directional mapping

    //Card is parent wrt Book
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    List<Book> bookIssued = new ArrayList<>();

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    List<Transactions> transactionsList = new ArrayList<>();
    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Student getStudentVariableName() {
        return studentVariableName;
    }

    public void setStudentVariableName(Student studentVariableName) {
        this.studentVariableName = studentVariableName;
    }

    public List<Book> getBookIssued() {
        return bookIssued;
    }

    public void setBookIssued(List<Book> bookIssued) {
        this.bookIssued = bookIssued;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }
}

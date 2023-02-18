package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionsService {
    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;
    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{
        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        //Get the book entity and card entity so that we will set the attribute before saving

        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        //Final goal is to set the transaction entity and save it
        Transactions transactions = new Transactions();

        //setting the attribute
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setIssueOperation(true);
        transactions.setTransactionStatus(TransactionStatus.PENDING);


        //Attribute left is success and failure
        //check for validation
        if(book == null || book.isIssued() == true){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionsRepository.save(transactions);
            throw new Exception("Book is not Available.");
        }
        if(card == null || card.getCardStatus() != CardStatus.ACTIVATED){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionsRepository.save(transactions);
            throw new Exception("Card is not Valid.");
        }

        //We have reached the success Now
        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        //Set the Attribute of Book
        book.setIssued(true);
        List<Transactions> listOfTransactionsForBook = book.getTransactionsList();
        listOfTransactionsForBook.add(transactions);
        book.setTransactions(listOfTransactionsForBook);

        //I need to make change in card too
        //Book and card
        List<Book> issueBookForCard = card.getBookIssued();
        issueBookForCard.add(book);
        card.setBookIssued(issueBookForCard);

        //Card and the transaction : bidirectional(parent class)
        List<Transactions> listOfTransactionsForCard = card.getTransactionsList();
        listOfTransactionsForCard.add(transactions);
        card.setTransactionsList(listOfTransactionsForCard);

        //save the parent
        cardRepository.save(card);
        //Autonatically the book and transaction will saved by cascading
        //Saving the parent

        return "Book issued Successfully.";
    }
    public String getTransactions(int bookId,int cardId){
        List<Transactions> transactionsList = transactionsRepository.getTransactionsForBookAndCard(bookId,cardId);
        String transactionId = transactionsList.get(0).getTransactionId();
        return  transactionId;
    }
}

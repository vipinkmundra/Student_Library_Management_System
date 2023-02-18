package com.example.Student_Library_Management_System.Repositories;

import com.example.Student_Library_Management_System.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Integer> {

    @Query(value = "Select * from transactions T where T.book_id =:bookId and T.card_id=:cardId and T.is_issue_operation=true",nativeQuery = true)
    List<Transactions> getTransactionsForBookAndCard(int bookId,int cardId);
}

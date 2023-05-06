package com.example.Student_Library_Management_System.Repositories;


import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Integer> {

    @Query(value = "select * from transactions t where t.card_id=:cardId and t.book_id=:bookId and t.is_issue_operation=:isIssue",nativeQuery = true)
    public List<Transactions> find(int cardId, int bookId, boolean isIssue);
}

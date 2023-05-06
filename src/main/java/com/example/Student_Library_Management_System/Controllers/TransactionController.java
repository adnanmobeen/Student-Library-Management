package com.example.Student_Library_Management_System.Controllers;


import com.example.Student_Library_Management_System.RequestDTOs.IssueBookRequestDTO;
import com.example.Student_Library_Management_System.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public String issueBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO) {

        try {
            return transactionService.issueBook(issueBookRequestDTO);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam("cardId")int cardId,@RequestParam("bookId")int bookId) throws Exception{
        return transactionService.returnBook(cardId,bookId);
    }
}

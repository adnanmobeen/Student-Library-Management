package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import com.example.Student_Library_Management_System.RequestDTOs.IssueBookRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    @Value("${booksIssued.max_allowed}")
    public
    int max_allowed_books;

    @Value("${booksIssued.max_allowed_days}")
    public
    int getMax_allowed_days;

    @Value("${booksIssued.fine.per_day}")
    public
    int fine_per_day;

    public String issueBook( IssueBookRequestDTO issueBookRequestDTO) throws Exception{

        int bookId = issueBookRequestDTO.getBookId();

        int cardId = issueBookRequestDTO.getCardId();

        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();

        Transactions transactions = new Transactions();

        //set its attributes.

        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setIssueOperation(true);
        transactions.setTransactionStatus(TransactionStatus.PENDING);


        //check for validation
        if(book == null || book.isIssued() == true){

            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Book is not available.");
        }

        if(card == null || (card.getCardstatus())!=(CardStatus.ACTIVATED)){
            transactions.setTransactionStatus(TransactionStatus.FAILED);

            transactionRepository.save(transactions);

            throw new Exception("Card is not valid");
        }

        if(card.getBooksIssued().size() >= max_allowed_books){
           transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
           throw new Exception("Book limit has reached for this card.");
        }

        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        //set attributes of book

        book.setIssued(true);
        List<Transactions>transactionsListforBook = book.getTransactionsListForBook();
        transactionsListforBook.add(transactions);
        book.setTransactionsListForBook(transactionsListforBook);

        List<Book>bookList = card.getBooksIssued();
        bookList.add(book);
        card.setBooksIssued(bookList);

        List<Transactions>transactionsListforCard = card.getTransactionsListForCard();
        transactionsListforCard.add(transactions);
        card.setTransactionsListForCard(transactionsListforCard);



        cardRepository.save(card);



        return "Book issued successfully.";
    }


    public String returnBook( int cardId, int bookId) throws Exception{

//        int bookId = issueBookRequestDTO.getBookId();
//        int cardId = issueBookRequestDTO.getCardId();


        //------->here i get error while passing transaction status.
        List<Transactions>transactionsList = transactionRepository.find(cardId,bookId,true );

        Transactions transactions = transactionsList.get(transactionsList.size()-1);


         Date issueDate = transactions.getTransactionDate();
       long total_issue_time = Math.abs(System.currentTimeMillis()-issueDate.getTime());
        long no_of_days_passed = TimeUnit.DAYS.convert(total_issue_time,TimeUnit.MILLISECONDS);


        int fines  = 0;
        if(no_of_days_passed > getMax_allowed_days){
            fines =  (int)((no_of_days_passed-getMax_allowed_days)*fine_per_day);
        }
        Book book = transactions.getBook();

        book.setIssued(false);
        book.setCard(null);
        bookRepository.save(book);

        Transactions tr = new Transactions();
        tr.setFine(fines);
        tr.setBook(book);
        tr.setCard(transactions.getCard());
        tr.setIssueOperation(false);
        tr.setTransactionStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(tr);



        return "Book returned successfully.";

    }
}

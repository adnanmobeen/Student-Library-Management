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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @Enumerated(value = EnumType.STRING)  //this is done for spring so that spring can identify the data type of
                                          //card status in table.
    private CardStatus cardstatus;

    @OneToOne  //mapping of card with student will be one to one
    @JoinColumn //it is used to join the both student and card table using foreign key which is pri key of student
    private Student student;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
     private List<Book>booksIssued;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transactions>transactionsListForCard;

    public Card() {
        booksIssued =  new ArrayList<>();
    }

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public List<Transactions> getTransactionsListForCard() {
        return transactionsListForCard;
    }

    public void setTransactionsListForCard(List<Transactions> transactionsListForCard) {
        this.transactionsListForCard = transactionsListForCard;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CardStatus getCardstatus() {
        return cardstatus;
    }

    public void setCardstatus(CardStatus cardstatus) {
        this.cardstatus = cardstatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}

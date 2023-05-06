package com.example.Student_Library_Management_System.ResponseDTOs;

import java.util.ArrayList;
import java.util.List;

public class AuthorResponse {

    private String name;

    private int age;

    private String country;

    private double rating;

    private List<BookResponse>booksWritten;

    public AuthorResponse() {
        booksWritten = new ArrayList<>();
    }

    public List<BookResponse> getBooksWritten() {
        return booksWritten;
    }

    public void setBooksWritten(List<BookResponse> booksWritten) {
        this.booksWritten = booksWritten;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

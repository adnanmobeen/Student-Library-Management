package com.example.Student_Library_Management_System.ResponseDTOs;

import com.example.Student_Library_Management_System.Enums.Genre;

public class BookResponse {

    private String name;

    private int pages;

    private Genre genre;

    public BookResponse() {
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
}

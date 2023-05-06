package com.example.Student_Library_Management_System.EntryDTOs;

public class AuthorEntryDTO {

    //this is just an object which is used to send the request from postman to make an entry for author.
    //it will only contain the parameter which will be required to create an author.
    //here id is not present because we don't want to send it from postman.

    private String name;

    private  int age;

    private String country;

    private double rating;

    public AuthorEntryDTO() {

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

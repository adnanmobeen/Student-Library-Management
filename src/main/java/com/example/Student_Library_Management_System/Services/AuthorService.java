package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.EntryDTOs.AuthorEntryDTO;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.ResponseDTOs.AuthorResponse;
import com.example.Student_Library_Management_System.ResponseDTOs.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(AuthorEntryDTO authorEntryDTO){

        //now create an original author and set its value which is coming from postman through authorEntryDto.
        //we can only save entity models in database so before save we need to convert a general object into
        //entity and previously in author class we defined author as @entity.

        Author author = new Author();

        author.setName(authorEntryDTO.getName());
        author.setAge(authorEntryDTO.getAge());
        author.setCountry(authorEntryDTO.getCountry());
        author.setRating(authorEntryDTO.getRating());

        authorRepository.save(author);

        return "Author added successfully.";
    }

    public AuthorResponse getAuthor(int authorId){

       Author author = authorRepository.findById(authorId).get();

       AuthorResponse authorResponse = new AuthorResponse();

       authorResponse.setName(author.getName());
       authorResponse.setAge(author.getAge());
       authorResponse.setCountry(author.getCountry());
       authorResponse.setRating(author.getRating());


        List<Book>bookList = author.getBooks_by_author();

        List<BookResponse>bookResponses = new ArrayList<>();

        for (Book book:bookList){
            BookResponse bookResponse = new BookResponse();
            bookResponse.setGenre(book.getGenre());
            bookResponse.setName(book.getName());
            bookResponse.setPages(book.getPages());

            bookResponses.add(bookResponse);
        }
        authorResponse.setBooksWritten(bookResponses);

        return authorResponse;

    }
}

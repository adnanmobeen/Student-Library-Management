package com.example.Student_Library_Management_System.Controllers;


import com.example.Student_Library_Management_System.EntryDTOs.AuthorEntryDTO;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.ResponseDTOs.AuthorResponse;
import com.example.Student_Library_Management_System.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add_author")
    public String addAuthor(@RequestBody AuthorEntryDTO authorEntryDTO){

        return authorService.addAuthor(authorEntryDTO);
    }

    @GetMapping("/getAuthor")
    public AuthorResponse getAuthor(@RequestParam("authorId") int authorId){
        return authorService.getAuthor(authorId);
    }

}

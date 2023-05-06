package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.EntryDTOs.BookEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookEntryDTO bookEntryDTO){

        int authorId = bookEntryDTO.getAuthorId();

        Author author = authorRepository.findById(authorId).get();

        //now set all attributes of book entity.
        Book book = new Book();
        book.setName(bookEntryDTO.getName());
        book.setPages(bookEntryDTO.getPages());
        book.setGenre(bookEntryDTO.getGenre());
        book.setIssued(false);
        //from book entity
        book.setAuthor(author);

            //from author entity
            List<Book>current_books_written = author.getBooks_by_author();
            current_books_written.add(book);

            //now the book is to be saved ,but author also to be saved(update)
            //because author entity has been updated as one more book added in book list by the name of author
            //so ,it will require an update
            authorRepository.save(author);
            // .save() function works as save as well as update as whenever prim key will present in table
            // then it work as update otherwise it create new object and save it in the table.

            // bookRepository.save() is not required bcz it will be autocalled by cascading effect.

        return "Book added successfully.";
    }
}

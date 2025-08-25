package me.yeaton.mediatracker.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.yeaton.mediatracker.model.Book;
import me.yeaton.mediatracker.model.bookDetails.BookDetail;
import me.yeaton.mediatracker.service.BookService;
import me.yeaton.mediatracker.service.BookService.GoogleBookResult;
import me.yeaton.mediatracker.service.BookService.SerializedBook;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Create
    @PostMapping
    public Book createBook(@RequestBody SerializedBook serializedBook) {
        return bookService.createBook(serializedBook);
    }

    // Read All
    @GetMapping
    public Iterable<Book> fetchBooks() {
        return bookService.fetchBooks();
    }

    // Read one (details)
    @GetMapping("/{id}")
    public BookDetail fetchBookDetails(@PathVariable("id") UUID id) {
        return bookService.fetchBookDetails(id);
    }


    // Update
    @PutMapping("/{id}")
    public Book updateBook(@RequestBody SerializedBook serializedBook, @PathVariable("id") UUID id) {
        return bookService.updateBook(serializedBook, id);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") UUID id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // External Search
    @GetMapping("/external")
    public GoogleBookResult externalFetchBooks(@RequestParam String author, @RequestParam String title) {
        return bookService.externalFetchBooks(title, author);
    }

    // External Add
    @PostMapping("/external/{id}")
    public Book externalCreateBook(@PathVariable("id") String id) {
        return bookService.createBookFromGoogleBooks(id);
    }
    
    

}

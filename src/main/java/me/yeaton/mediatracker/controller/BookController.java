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
import me.yeaton.mediatracker.service.BookService;
import me.yeaton.mediatracker.service.BookService.SerializedBook;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


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

    // Read
    @GetMapping
    public Iterable<Book> fetchBooks() {
        return bookService.fetchBooks();
    }

    // Update
    @PutMapping("/{id}")
    public Book putMethodName(@RequestBody SerializedBook serializedBook, @PathVariable("id") UUID id) {
        return bookService.updateBook(serializedBook, id);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") UUID id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

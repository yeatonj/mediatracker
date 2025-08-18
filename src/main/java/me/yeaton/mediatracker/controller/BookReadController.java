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

import me.yeaton.mediatracker.model.BookRead;
import me.yeaton.mediatracker.service.BookReadService;
import me.yeaton.mediatracker.service.BookReadService.SerializedBookRead;
import me.yeaton.mediatracker.service.BookService.SerializedBook;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/books-read")
public class BookReadController {

    @Autowired
    private BookReadService bookReadService;

    // Create
    @PostMapping
    public BookRead createBookRead(@RequestBody SerializedBookRead serializedBookRead) {
        return bookReadService.createBookRead(serializedBookRead);
    }

    // Read
    @GetMapping
    public Iterable<BookRead> fetchBooksRead() {
        return bookReadService.fetchBookRead();
    }

    // Update
    @PutMapping("/{id}")
    public BookRead updateBookRead(@PathVariable("id") UUID id, @RequestBody SerializedBookRead serializedBookRead) {
        return bookReadService.updateBookRead(serializedBookRead, id);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookRead(@PathVariable("id") UUID id) {
        bookReadService.deleteBookRead(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

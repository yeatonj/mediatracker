package me.yeaton.mediatracker.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.yeaton.mediatracker.model.Author;
import me.yeaton.mediatracker.service.AuthorService;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

     @Autowired
    private AuthorService authorService;

    // Create
    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    // Read
    @GetMapping
    public Iterable<Author> fetchAuthors() {
        return authorService.fetchAuthors();
    }

    // Update
    @PutMapping("/{id}")
    public Author updateAuthor(@RequestBody Author author, @PathVariable("id") UUID id) {
        return authorService.updateAuthor(author, id);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") UUID id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}

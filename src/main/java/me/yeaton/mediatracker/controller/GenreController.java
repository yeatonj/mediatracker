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

import me.yeaton.mediatracker.model.Genre;
import me.yeaton.mediatracker.service.GenreService;

@RestController
@RequestMapping("api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    // Create
    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    // Read
    @GetMapping
    public Iterable<Genre> fetchGenres() {
        return genreService.fetchGenres();
    }

    // Update
    @PutMapping("/{id}")
    public Genre updateGenre(@RequestBody Genre genre, @PathVariable("id") UUID id) {
        return genreService.updateGenre(genre, id);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("id") UUID id) {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}

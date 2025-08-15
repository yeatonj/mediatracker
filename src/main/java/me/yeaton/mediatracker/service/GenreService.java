package me.yeaton.mediatracker.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.yeaton.mediatracker.model.Genre;
import me.yeaton.mediatracker.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    // Create
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    // Read
    public Iterable<Genre> fetchGenres() {
        return genreRepository.findAll();
    }

    // Update
    public Genre updateGenre(Genre genre, UUID id) {
        Genre genreDB = genreRepository.findById(id).get();
        if (Objects.nonNull(genre.getGenre()) && !"".equalsIgnoreCase(genre.getGenre())) {
            genreDB.setGenre(genre.getGenre());
        }
        return genreRepository.save(genreDB);
    }

    // Delete
    public void deleteGenre(UUID id) {
        genreRepository.deleteById(id);
    }
}

package me.yeaton.mediatracker.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.yeaton.mediatracker.model.Author;
import me.yeaton.mediatracker.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Create
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Read
    public Iterable<Author> fetchAuthors() {
        return authorRepository.findAll();
    }

    // Update
    public Author updateAuthor(Author author, UUID id) {
        Author authorDB = authorRepository.findById(id).get();
        if (Objects.nonNull(author.getName()) && !"".equalsIgnoreCase(author.getName())) {
            authorDB.setName(author.getName());
        }
        return authorRepository.save(authorDB);
    }

    // Delete
    public void deleteAuthor(UUID id) {
        authorRepository.deleteById(id);
    }
}

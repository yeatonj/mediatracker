package me.yeaton.mediatracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import me.yeaton.mediatracker.model.Author;

public interface AuthorRepository extends CrudRepository<Author, UUID> {
    Optional<Author> findByName(String name);

}

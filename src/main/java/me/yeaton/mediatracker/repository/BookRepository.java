package me.yeaton.mediatracker.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import me.yeaton.mediatracker.model.Book;

public interface BookRepository extends CrudRepository<Book, UUID>{

}

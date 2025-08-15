package me.yeaton.mediatracker.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import me.yeaton.mediatracker.model.BookRead;

public interface BookReadRepository extends CrudRepository<BookRead, UUID>{

}

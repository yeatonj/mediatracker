package me.yeaton.mediatracker.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import me.yeaton.mediatracker.model.Genre;

public interface GenreRepository extends CrudRepository<Genre, UUID>{

}

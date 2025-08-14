package me.yeaton.mediatracker.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import me.yeaton.mediatracker.model.Tag;

public interface TagRepository extends CrudRepository<Tag, UUID> {

}

package me.yeaton.mediatracker.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import me.yeaton.mediatracker.model.Series;

public interface SeriesRepository extends CrudRepository<Series, UUID> {

}

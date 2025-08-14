package me.yeaton.mediatracker.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public record Genre(
    @Id UUID id, 
    String genre) {
}

package me.yeaton.mediatracker.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public record Tag(
    @Id UUID id, 
    String tag) {
}

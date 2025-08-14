package me.yeaton.mediatracker.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Genre {
    @Id 
    private UUID id;
    private String genre;

    public Genre(String genre) {
        this.genre = genre;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre [id=" + id + ", genre=" + genre + "]";
    }
}

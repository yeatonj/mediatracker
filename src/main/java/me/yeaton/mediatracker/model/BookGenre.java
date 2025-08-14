package me.yeaton.mediatracker.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class BookGenre {

    @Id
    UUID id;
    AggregateReference<Genre, UUID> genre;
    @Transient
    Book book;

    public BookGenre(AggregateReference<Genre, UUID> genre) {
        this.genre = genre;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AggregateReference<Genre, UUID> getGenre() {
        return genre;
    }

    public void setGenre(AggregateReference<Genre, UUID> genre) {
        this.genre = genre;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookGenre [id=" + id + ", genre=" + genre + ", book=" + book + "]";
    }

    
}

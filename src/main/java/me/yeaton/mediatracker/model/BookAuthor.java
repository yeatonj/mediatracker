package me.yeaton.mediatracker.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class BookAuthor {

    @Id
    UUID id;
    AggregateReference<Author, UUID> author;

    public BookAuthor(AggregateReference<Author, UUID> author) {
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AggregateReference<Author, UUID> getAuthor() {
        return author;
    }

    public void setAuthor(AggregateReference<Author, UUID> author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookAuthor [id=" + id + ", author=" + author + "]";
    }
}

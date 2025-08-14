package me.yeaton.mediatracker.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class BookTag {

    @Id
    UUID id;
    AggregateReference<Tag, UUID> tag;
    @Transient
    Book book;

    public BookTag(AggregateReference<Tag, UUID> tag) {
        this.tag = tag;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AggregateReference<Tag, UUID> getTag() {
        return tag;
    }

    public void setTag(AggregateReference<Tag, UUID> tag) {
        this.tag = tag;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookTag [id=" + id + ", tag=" + tag + ", book=" + book + "]";
    }

    
}

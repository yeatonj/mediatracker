package me.yeaton.mediatracker.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class BookWishlist {

    @Id
    private UUID id;
    private Integer rank;
    private AggregateReference<Book, UUID> book;
    private AggregateReference<UserMain, UUID> userMain;

    public BookWishlist(AggregateReference<Book, UUID> book, AggregateReference<UserMain, UUID> userMain) {
        this.userMain = userMain;
        this.book = book;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public AggregateReference<Book, UUID> getBook() {
        return book;
    }

    public void setBook(AggregateReference<Book, UUID> book) {
        this.book = book;
    }

    public AggregateReference<UserMain, UUID> getUserMain() {
        return userMain;
    }

    public void setUserMain(AggregateReference<UserMain, UUID> userMain) {
        this.userMain = userMain;
    }

    @Override
    public String toString() {
        return "BookWishlist [id=" + id + ", rank=" + rank + ", book=" + book + ", userMain=" + userMain + "]";
    }

}

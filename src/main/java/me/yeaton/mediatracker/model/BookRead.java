package me.yeaton.mediatracker.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class BookRead {

    @Id
    private UUID id;
    private Integer progress;
    private Integer rating;
    private String review;
    private Boolean completed;
    private LocalDateTime completedDate;
    private AggregateReference<Book, UUID> book;
    private AggregateReference<UserMain, UUID> userMain;
    private Set<UserTag> userTags = new HashSet<>();

    // !! need to put validation in for progress and rating

    public BookRead() {}

    public BookRead(AggregateReference<Book, UUID> book, AggregateReference<UserMain, UUID> userMain) {
        this(book, userMain, 0, null, null, false, null);
    }

    public BookRead(AggregateReference<Book, UUID> book, AggregateReference<UserMain, UUID> userMain, Integer progress, Integer rating, String review, Boolean completed, LocalDateTime completedDate) {
        this.book = book;
        this.userMain = userMain;
        this.progress = progress;
        this.rating = rating;
        this.review = review;
        this.completed = completed;
        this.completedDate = completedDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDateTime completedDate) {
        this.completedDate = completedDate;
    }

    public AggregateReference<Book, UUID> getBook() {
        return book;
    }

    public void setBook(AggregateReference<Book, UUID> book) {
        this.book = book;
    }

    public Set<UserTag> getUserTags() {
        return userTags;
    }

    public void setUserTags(Set<UserTag> userTags) {
        this.userTags = userTags;
    }

    public void addUserTag(UserTag userTag) {
        this.userTags.add(userTag);
        userTag.bookRead = this;
    }

    public AggregateReference<UserMain, UUID> getUserMain() {
        return userMain;
    }

    public void setUserMain(AggregateReference<UserMain, UUID> userMain) {
        this.userMain = userMain;
    }

    @Override
    public String toString() {
        return "BookRead [id=" + id + ", progress=" + progress + ", rating=" + rating + ", review=" + review
                + ", completed=" + completed + ", completedDate=" + completedDate + ", book=" + book + ", userMain="
                + userMain + ", userTags=" + userTags + "]";
    }

}

package me.yeaton.mediatracker.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
    private Set<UserTag> tags = new HashSet<>();
    @Transient
    UserMain userMain; // This is the user associated with this wishlist, will be persisted by the user itself

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

    public Set<UserTag> getTags() {
        return tags;
    }

    public void setTags(Set<UserTag> tags) {
        this.tags = tags;
    }

    public UserMain getUserMain() {
        return userMain;
    }

    public void setUserMain(UserMain userMain) {
        this.userMain = userMain;
    }

    @Override
    public String toString() {
        return "BookRead [id=" + id + ", progress=" + progress + ", rating=" + rating + ", review=" + review
                + ", completed=" + completed + ", completedDate=" + completedDate + ", book=" + book + ", tags=" + tags
                + ", userMain=" + userMain + "]";
    }

    

    
    

}

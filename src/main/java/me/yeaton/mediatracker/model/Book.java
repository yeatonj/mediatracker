package me.yeaton.mediatracker.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class Book {

    @Id
    private UUID id;
    private String title;
    private AggregateReference<Series, UUID> series;
    private Integer pages;
    private String description;
    private LocalDateTime published;
    private String coverImgLoc;
    // Associated Authors
    private Set<BookAuthor> bookAuthors = new HashSet<>();
    // Associated Genres
    private Set<BookGenre> bookGenres = new HashSet<>();
    // Associated Tags
    private Set<BookTag> bookTags = new HashSet<>();

    
    public Book(String title, Integer pages, String description, LocalDateTime published) {
        // !! need to re-add author
        this.title = title;
        this.pages = pages;
        this.description = description;
        this.published = published;
    }


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public AggregateReference<Series, UUID> getSeries() {
        return series;
    }


    public void setSeries(AggregateReference<Series, UUID> series) {
        this.series = series;
    }


    public Integer getPages() {
        return pages;
    }


    public void setPages(Integer pages) {
        this.pages = pages;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDateTime getPublished() {
        return published;
    }


    public void setPublished(LocalDateTime published) {
        this.published = published;
    }


    public String getCoverImgLoc() {
        return coverImgLoc;
    }


    public void setCoverImgLoc(String coverImgLoc) {
        this.coverImgLoc = coverImgLoc;
    }


    public Set<BookGenre> getBookGenres() {
        return bookGenres;
    }


    public void setBookGenres(Set<BookGenre> bookGenres) {
        this.bookGenres = bookGenres;
    }

    public void addBookGenre(BookGenre bookGenre) {
        this.bookGenres.add(bookGenre);
    }


    public Set<BookTag> getBookTags() {
        return bookTags;
    }


    public void setBookTags(Set<BookTag> bookTags) {
        this.bookTags = bookTags;
    }

    public void addBookTag(BookTag bookTag) {
        this.bookTags.add(bookTag);
    }


    public Set<BookAuthor> getBookAuthors() {
        return bookAuthors;
    }


    public void setBookAuthors(Set<BookAuthor> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public void addBookAuthor(BookAuthor bookAuthor) {
        this.bookAuthors.add(bookAuthor);
    }


    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", series=" + series + ", pages=" + pages + ", description="
                + description + ", published=" + published + ", coverImgLoc=" + coverImgLoc + ", bookAuthors="
                + bookAuthors + ", bookGenres=" + bookGenres + ", bookTags=" + bookTags + "]";
    }

}

package me.yeaton.mediatracker.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Book {

    @Id
    private UUID id;
    private String title;
    private String author;
    private String series;
    private Integer pages;
    private String description;
    private LocalDateTime published;
    private String coverImgLoc;
    // Associated Genres
    private Set<BookGenre> bookGenres = new HashSet<>();
    // Associated Tags
    private Set<BookTag> bookTags = new HashSet<>();

    
    public Book(String title, String author, Integer pages, String description, LocalDateTime published) {
        this.title = title;
        this.author = author;
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


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getSeries() {
        return series;
    }


    public void setSeries(String series) {
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


    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", series=" + series + ", pages=" + pages
                + ", description=" + description + ", published=" + published + ", coverImgLoc=" + coverImgLoc
                + ", bookGenres=" + bookGenres + ", bookTags=" + bookTags + "]";
    }

    

    

}

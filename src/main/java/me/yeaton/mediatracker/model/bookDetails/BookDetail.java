package me.yeaton.mediatracker.model.bookDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BookDetail {
    private UUID id;
    private String title;
    private String series;
    private Integer pages;
    private String description;
    private LocalDateTime published;
    private String coverImgLoc;
    private List<String> genres;
    private List<UUID> genreIds;
    private List<String> tags;
    private List<UUID> tagIds;
    private List<UUID> authorIds;
    private List<String> authors;
    public UUID getId() {
        return id;
    }
    public void setId(UUID bookId) {
        this.id = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public List<UUID> getGenreIds() {
        return genreIds;
    }
    public void setGenreIds(List<UUID> genreIds) {
        this.genreIds = genreIds;
    }
    public List<UUID> getTagIds() {
        return tagIds;
    }
    public void setTagIds(List<UUID> tagIds) {
        this.tagIds = tagIds;
    }
    public List<UUID> getAuthorIds() {
        return authorIds;
    }
    public void setAuthorIds(List<UUID> authorIds) {
        this.authorIds = authorIds;
    }
    public List<String> getAuthors() {
        return authors;
    }
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
    

    
}

package me.yeaton.mediatracker.model.bookDetails;

import java.util.List;
import java.util.UUID;

public class BookOverview {
    private UUID id;
    private String title;
    private String coverImgLoc;
    private List<UUID> authorIds;
    private List<String> authors;
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
    public String getCoverImgLoc() {
        return coverImgLoc;
    }
    public void setCoverImgLoc(String coverImgLoc) {
        this.coverImgLoc = coverImgLoc;
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

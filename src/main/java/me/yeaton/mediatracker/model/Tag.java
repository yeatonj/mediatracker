package me.yeaton.mediatracker.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Tag {
    @Id 
    private UUID id; 
    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Tag [id=" + id + ", tag=" + tag + "]";
    }
}   

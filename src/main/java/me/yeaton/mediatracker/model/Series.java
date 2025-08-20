package me.yeaton.mediatracker.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Series {

    @Id
    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Series [id=" + id + ", name=" + name + "]";
    }
}

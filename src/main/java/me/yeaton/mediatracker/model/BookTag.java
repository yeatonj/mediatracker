package me.yeaton.mediatracker.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class BookTag {

    @Id
    UUID id;
    AggregateReference<Tag, UUID> tag;

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

    @Override
    public String toString() {
        return "BookTag [id=" + id + ", tag=" + tag + "]";
    }

    
}

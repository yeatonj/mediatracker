package me.yeaton.mediatracker.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class UserTag {

    @Id
    private UUID id;
    AggregateReference<Tag, UUID> tag;
    @Transient
    BookRead bookRead;

    public UserTag(AggregateReference<Tag, UUID> tag) {
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

    public BookRead getBookRead() {
        return bookRead;
    }

    public void setBookRead(BookRead bookRead) {
        this.bookRead = bookRead;
    }

    @Override
    public String toString() {
        return "UserTag [id=" + id + ", tag=" + tag + ", bookRead=" + bookRead + "]";
    }
}

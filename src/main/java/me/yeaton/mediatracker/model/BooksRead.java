package me.yeaton.mediatracker.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class BooksRead {

    @Id
    private UUID id;
    private Integer progress;
    private Integer rating;
    private String review;
    private Boolean completed;
    private LocalDateTime completedDate;

}

package me.yeaton.mediatracker.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import me.yeaton.mediatracker.model.BookRead;
import me.yeaton.mediatracker.model.UserTag;
import me.yeaton.mediatracker.repository.BookReadRepository;
import me.yeaton.mediatracker.repository.BookRepository;
import me.yeaton.mediatracker.repository.TagRepository;
import me.yeaton.mediatracker.repository.UserRepository;

@Service
public class BookReadService {

    @Autowired 
    BookReadRepository bookReadRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    // class for recieving bookread given the Book and User Aggregates
    public record SerializedBookRead(
        Integer progress,
        Integer rating,
        String review,
        Boolean completed,
        LocalDateTime completedDate,
        UUID bookId,
        UUID userId,
        List<UUID> tags
    ){}

    private BookRead deserializeBookRead(SerializedBookRead serializedBookRead) {
        BookRead tempBookRead = new BookRead();
        tempBookRead.setProgress(serializedBookRead.progress());
        tempBookRead.setCompleted(serializedBookRead.completed());
        tempBookRead.setRating(serializedBookRead.rating());
        tempBookRead.setReview(serializedBookRead.review());
        tempBookRead.setCompletedDate(serializedBookRead.completedDate());
        AggregateReference.to(bookReadRepository.findById(serializedBookRead.bookId()).get().getId());
        AggregateReference.to(userRepository.findById(serializedBookRead.userId()).get().getId());
        // !! want better error handling here...
        Set<UserTag> tempSet = new HashSet<>();
        // Add tags
        for (UUID tag : serializedBookRead.tags()) {
            tempSet.add(new UserTag(AggregateReference.to(tagRepository.findById(tag).get().getId())));
        }
        return tempBookRead;
    }

    // Create
    public BookRead createBookRead(SerializedBookRead serializedBookRead) {
        return bookReadRepository.save(deserializeBookRead(serializedBookRead));
    }

    // Read
    public Iterable<BookRead> fetchBookRead() {
        return bookReadRepository.findAll();
    }

    // Update
    public BookRead updateBookRead(SerializedBookRead serializedBookRead, UUID id) {
        BookRead bookRead = deserializeBookRead(serializedBookRead);
        BookRead bookReadDB = bookReadRepository.findById(id).get();
        // Update progress
        if (Objects.nonNull(bookRead.getProgress())) {
            bookReadDB.setProgress(bookRead.getProgress());
        }
        // Update rating
        if (Objects.nonNull(bookRead.getRating())) {
            bookReadDB.setRating(bookRead.getRating());
        }

        // Update review
        if (Objects.nonNull(bookRead.getReview()) && !"".equalsIgnoreCase(bookRead.getReview())) {
            bookReadDB.setReview(bookRead.getReview());
        }
        // Update completed
        if (Objects.nonNull(bookRead.getCompleted())) {
            bookReadDB.setCompleted(bookRead.getCompleted());
        }
        // update completed date
        if (Objects.nonNull(bookRead.getCompletedDate())) {
            bookReadDB.setCompletedDate(bookRead.getCompletedDate());
        }
        // Update tags
        if (Objects.nonNull(bookRead.getUserTags())) {
            bookReadDB.setUserTags(bookRead.getUserTags());
        }
        // Can't update book or user references
        return bookReadRepository.save(bookReadDB);
    }

    // Delete
    public void deleteBookRead(UUID id) {
        bookReadRepository.deleteById(id);
    }

}

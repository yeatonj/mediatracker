package me.yeaton.mediatracker.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.yeaton.mediatracker.model.BookRead;
import me.yeaton.mediatracker.repository.BookReadRepository;

@Service
public class BookReadService {

    @Autowired 
    BookReadRepository bookReadRepository;

    // Create
    public BookRead createBookRead(BookRead bookRead) {
        return bookReadRepository.save(bookRead);
    }

    // Read
    public Iterable<BookRead> fetchBookRead() {
        return bookReadRepository.findAll();
    }

    // Update
    public BookRead updateBookRead(BookRead bookRead, UUID id) {
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

package me.yeaton.mediatracker.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import me.yeaton.mediatracker.model.Book;
import me.yeaton.mediatracker.model.BookWishlist;
import me.yeaton.mediatracker.model.UserMain;
import me.yeaton.mediatracker.repository.BookRepository;
import me.yeaton.mediatracker.repository.BookWishlistRepository;
import me.yeaton.mediatracker.repository.UserRepository;

@Service
public class BookWishlistService {

    @Autowired
    private BookWishlistRepository bookWishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public record SerializedBookWishlist(
        Integer rank,
        UUID bookId,
        UUID userId
    ) {}

    private BookWishlist deserializeBookWishlist(SerializedBookWishlist serializedBookWishlist) {
        AggregateReference<Book, UUID> book = null;
        AggregateReference<UserMain, UUID> user = null;
        if (serializedBookWishlist.bookId() != null) {
            book = AggregateReference.to(bookRepository.findById(serializedBookWishlist.bookId()).get().getId());
        }
        if (serializedBookWishlist.bookId() != null) {
            user = AggregateReference.to(userRepository.findById(serializedBookWishlist.userId()).get().getId());
        }
        return new BookWishlist(book, user, serializedBookWishlist.rank());
    }

    // Create
    public BookWishlist createBookWishlist(SerializedBookWishlist serializedBookWishlist) {
        return bookWishlistRepository.save(deserializeBookWishlist(serializedBookWishlist));
    }

    // Read
    public Iterable<BookWishlist> fetchBookWishlist() {
        return bookWishlistRepository.findAll();
    }

    // Update
    public BookWishlist updateBookWishlist(SerializedBookWishlist serializedBookWishlist, UUID id) {
        BookWishlist bookWishlist = deserializeBookWishlist(serializedBookWishlist);
        BookWishlist bookWishlistDB = bookWishlistRepository.findById(id).get();
        // Can only update rank
        if (Objects.nonNull(bookWishlist.getRank())) {
            bookWishlistDB.setRank(bookWishlist.getRank());
        }

        return bookWishlistRepository.save(bookWishlistDB);
    }

    // Delete
    public void deleteBookWishlist(UUID id) {
        bookWishlistRepository.deleteById(id);
    }
}

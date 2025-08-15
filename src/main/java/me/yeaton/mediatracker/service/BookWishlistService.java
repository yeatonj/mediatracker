package me.yeaton.mediatracker.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.yeaton.mediatracker.model.BookWishlist;
import me.yeaton.mediatracker.repository.BookWishlistRepository;

@Service
public class BookWishlistService {

    @Autowired
    private BookWishlistRepository bookWishlistRepository;

    // Create
    public BookWishlist createBookWishlist(BookWishlist wishlist) {
        return bookWishlistRepository.save(wishlist);
    }

    // Read
    public Iterable<BookWishlist> fetchBookWishlist() {
        return bookWishlistRepository.findAll();
    }

    // Update
    public BookWishlist updateBookWishlist(BookWishlist bookWishlist, UUID id) {
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

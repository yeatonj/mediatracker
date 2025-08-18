package me.yeaton.mediatracker.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.yeaton.mediatracker.model.BookWishlist;
import me.yeaton.mediatracker.service.BookWishlistService;
import me.yeaton.mediatracker.service.BookWishlistService.SerializedBookWishlist;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/books-wishlist")
public class BookWishlistController {

    @Autowired
    private BookWishlistService bookWishlistService;

    // Create
    @PostMapping
    public BookWishlist createBookWishlist(@RequestBody SerializedBookWishlist serializedBookWishlist) {
        return bookWishlistService.createBookWishlist(serializedBookWishlist);
    }

    // Read
    @GetMapping
    public Iterable<BookWishlist> fetchBookWishlists() {
        return bookWishlistService.fetchBookWishlist();
    }

    // Update
    @PutMapping("/{id}")
    public BookWishlist updateBookWishlist(@PathVariable("id") UUID id, @RequestBody SerializedBookWishlist serializedBookWishlist) {
        return bookWishlistService.updateBookWishlist(serializedBookWishlist, id);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookWishlist(@PathVariable("id") UUID id) {
        bookWishlistService.deleteBookWishlist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package me.yeaton.mediatracker.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import me.yeaton.mediatracker.model.BookWishlist;

public interface WishlistRepository extends CrudRepository<BookWishlist, UUID>{

}

package me.yeaton.mediatracker.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import me.yeaton.mediatracker.model.BookWishlist;

public interface BookWishlistRepository extends CrudRepository<BookWishlist, UUID>{

}

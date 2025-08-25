package me.yeaton.mediatracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.transaction.annotation.Transactional;

import me.yeaton.mediatracker.model.Book;
import me.yeaton.mediatracker.model.BookGenre;
import me.yeaton.mediatracker.model.BookRead;
import me.yeaton.mediatracker.model.BookTag;
import me.yeaton.mediatracker.model.BookWishlist;
import me.yeaton.mediatracker.model.Genre;
import me.yeaton.mediatracker.model.Tag;
import me.yeaton.mediatracker.model.UserMain;
import me.yeaton.mediatracker.model.UserTag;
import me.yeaton.mediatracker.repository.BookReadRepository;
import me.yeaton.mediatracker.repository.BookRepository;
import me.yeaton.mediatracker.repository.GenreRepository;
import me.yeaton.mediatracker.repository.TagRepository;
import me.yeaton.mediatracker.repository.UserRepository;
import me.yeaton.mediatracker.repository.BookWishlistRepository;

// @SpringBootTest
// class MediatrackerApplicationTests {
// 	@Autowired
// 	UserRepository users;

// 	@Autowired
// 	TagRepository tags;

// 	@Autowired 
// 	GenreRepository genres;

// 	@Autowired
// 	BookRepository books;

// 	@Autowired
// 	BookReadRepository booksRead;

// 	@Autowired
// 	BookWishlistRepository wishlists;

// 	@Test
// 	void contextLoads() {
// 	}

// 	@Test
// 	public void createTest() {
// 		// Create a genre and a tag
// 		AggregateReference<Genre, UUID> testGenre = AggregateReference.to(genres.save(new Genre("Fantasy")).getId());
// 		AggregateReference<Tag, UUID> testTagBook = AggregateReference.to(tags.save(new Tag("Cosmere")).getId());
// 		AggregateReference<Tag, UUID> testTagUser = AggregateReference.to(tags.save(new Tag("Awesome")).getId());

// 		// Create a book and add the genre/tag
// 		Book book1 = new Book("The Way of Kings", "Brandon Sanderson", 755, "A book about Kaladin", LocalDateTime.now());
// 		book1.addBookGenre(new BookGenre(testGenre));
// 		book1.addBookTag(new BookTag(testTagBook));
// 		AggregateReference<Book, UUID> book1Ref = AggregateReference.to(books.save(book1).getId());

// 		// Create a book and add the genre/tag
// 		Book book2 = new Book("Words of Radiance", "Brandon Sanderson", 600, "A book about Eshonai", LocalDateTime.now());
// 		book2.addBookGenre(new BookGenre(testGenre));
// 		book2.addBookTag(new BookTag(testTagBook));
// 		AggregateReference<Book, UUID> book2Ref = AggregateReference.to(books.save(book2).getId());

// 		// Create a user
// 		UserMain testUser = new UserMain("dev", "dev@dev.com", "password");
// 		AggregateReference<UserMain, UUID> userRef = AggregateReference.to(users.save(testUser).getId());

// 		// Create a book read entry for this user and book 1, add a tag as well
// 		BookRead userBookRead = new BookRead(book1Ref, userRef);
// 		userBookRead.addUserTag(new UserTag(testTagUser));
// 		booksRead.save(userBookRead);

// 		// Create a wish list entry for this user and book 2
// 		BookWishlist userWishlist = new BookWishlist(book2Ref, userRef);
// 		wishlists.save(userWishlist);
// 	}

// 	@Test
// 	public void deleteTest() {
// 		booksRead.deleteAll();
// 		assertEquals(books.count(), 2);
// 		wishlists.deleteAll();
// 		users.deleteAll();
// 		books.deleteAll();
// 		tags.deleteAll();
// 		genres.deleteAll();
// 	}

// }

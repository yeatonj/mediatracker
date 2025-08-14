package me.yeaton.mediatracker;

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
import me.yeaton.mediatracker.repository.BookRepository;
import me.yeaton.mediatracker.repository.GenreRepository;
import me.yeaton.mediatracker.repository.TagRepository;
import me.yeaton.mediatracker.repository.UserRepository;

@SpringBootTest
class MediatrackerApplicationTests {
	@Autowired
	UserRepository users;

	@Autowired
	TagRepository tags;

	@Autowired 
	GenreRepository genres;

	@Autowired
	BookRepository books;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	public void createTest() {
		// Create a genre and a tag
		AggregateReference<Genre, UUID> testGenre = AggregateReference.to(genres.save(new Genre("Fantasy")).getId());
		AggregateReference<Tag, UUID> testTagBook = AggregateReference.to(tags.save(new Tag("Cosmere")).getId());
		AggregateReference<Tag, UUID> testTagUser = AggregateReference.to(tags.save(new Tag("Awesome")).getId());

		// Create a book and add the genre/tag
		Book book1 = new Book("The Way of Kings", "Brandon Sanderson", 755, "A book about Kaladin", LocalDateTime.now());
		book1.addBookGenre(new BookGenre(testGenre));
		book1.addBookTag(new BookTag(testTagBook));
		AggregateReference<Book, UUID> book1Ref = AggregateReference.to(books.save(book1).getId());

		// Create a book and add the genre/tag
		Book book2 = new Book("Words of Radiance", "Brandon Sanderson", 600, "A book about Eshonai", LocalDateTime.now());
		book2.addBookGenre(new BookGenre(testGenre));
		book2.addBookTag(new BookTag(testTagBook));
		AggregateReference<Book, UUID> book2Ref = AggregateReference.to(books.save(book2).getId());

		UserMain testUser = new UserMain("dev", "dev@dev.com", "password");
		testUser.addWishListBook(new BookWishlist(book2Ref));
		BookRead userBookRead = new BookRead(book1Ref);
		userBookRead.addUserTag(new UserTag(testTagUser));
		testUser.addBookRead(userBookRead);
		users.save(testUser);
	}

}

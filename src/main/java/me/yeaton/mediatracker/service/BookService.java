package me.yeaton.mediatracker.service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import me.yeaton.mediatracker.model.Author;
import me.yeaton.mediatracker.model.Book;
import me.yeaton.mediatracker.model.BookAuthor;
import me.yeaton.mediatracker.model.BookGenre;
import me.yeaton.mediatracker.model.BookTag;
import me.yeaton.mediatracker.model.Genre;
import me.yeaton.mediatracker.model.Tag;
import me.yeaton.mediatracker.model.bookDetails.BookDetail;
import me.yeaton.mediatracker.repository.AuthorRepository;
import me.yeaton.mediatracker.repository.BookRepository;
import me.yeaton.mediatracker.repository.GenreRepository;
import me.yeaton.mediatracker.repository.SeriesRepository;
import me.yeaton.mediatracker.repository.TagRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    // class for recieving books for the genre and tag many-many relationship
    public record SerializedBook(
        String title,
        UUID seriesId,
        Integer pages,
        String description,
        LocalDateTime published,
        String coverImgLoc,
        List<UUID> authors,
        List<UUID> genres,
        List<UUID> tags
    ){}

    private Book deserializeBook(SerializedBook serializedBook) {
        Book tempBook = new Book(serializedBook.title(), serializedBook.pages(), serializedBook.description(), serializedBook.published());
        if (serializedBook.seriesId() != null) {
            tempBook.setSeries(AggregateReference.to(seriesRepository.findById(serializedBook.seriesId()).get().getId()));
        }
        tempBook.setCoverImgLoc(serializedBook.coverImgLoc());
        // !! want better error handling here...
        // Add authors
        if (serializedBook.authors() != null) {
            for (UUID authorId : serializedBook.authors()) {
                // Grab aggregate reference to the relevant genre tag
                AggregateReference<Author,UUID> ref = AggregateReference.to(authorRepository.findById(authorId).get().getId());
                // add new BookGenre object to the Book
                tempBook.addBookAuthor(new BookAuthor(ref));
            }
        }
        // Add genres
        if (serializedBook.genres() != null) {
            for (UUID genreId : serializedBook.genres()) {
                // Grab aggregate reference to the relevant genre tag
                AggregateReference<Genre,UUID> ref = AggregateReference.to(genreRepository.findById(genreId).get().getId());
                // add new BookGenre object to the Book
                tempBook.addBookGenre(new BookGenre(ref));
            }
        }
        // Add tags
        if (serializedBook.tags() != null) {
            for (UUID tagId : serializedBook.tags()) {
                // Grab aggregate reference to the relevant genre tag
                AggregateReference<Tag,UUID> ref = AggregateReference.to(tagRepository.findById(tagId).get().getId());
                // add new BookGenre object to the Book
                tempBook.addBookTag(new BookTag(ref));
            }
        }
        return tempBook;
    }

    // Create
    public Book createBook(SerializedBook serializedBook) {
        return bookRepository.save(deserializeBook(serializedBook));
    }

    // Read All
    public Iterable<Book> fetchBooks() {
        return bookRepository.findAll();
    }

    // Read One
    public BookDetail fetchBookDetails(UUID id) {
        return bookRepository.findBookDetails(id);
    }

    // Update
    public Book updateBook(SerializedBook serializedBook, UUID id) {
        Book book = deserializeBook(serializedBook);
        Book bookDB = bookRepository.findById(id).get();
        // Update title
        if (Objects.nonNull(book.getTitle()) && !"".equalsIgnoreCase(book.getTitle())) {
            bookDB.setTitle(book.getTitle());
        }
        // Update authors
        if (Objects.nonNull(book.getBookAuthors())) {
            bookDB.setBookAuthors(book.getBookAuthors());
        }
        // Update series
        if (Objects.nonNull(book.getSeries())) {
            bookDB.setSeries(book.getSeries());
        }
        // Update pages
        if (Objects.nonNull(book.getPages())) {
            bookDB.setPages(book.getPages());
        }
        // Update description
        if (Objects.nonNull(book.getDescription()) && !"".equalsIgnoreCase(book.getDescription())) {
            bookDB.setDescription(book.getDescription());
        }
        // Update published
        if (Objects.nonNull(book.getPublished())) {
            bookDB.setPublished(book.getPublished());
        }
        // Update coverImgLoc
        if (Objects.nonNull(book.getCoverImgLoc()) && !"".equalsIgnoreCase(book.getCoverImgLoc())) {
            bookDB.setCoverImgLoc(book.getCoverImgLoc());
        }
        // Update Genres
        if (Objects.nonNull(book.getBookGenres())) {
            bookDB.setBookGenres(book.getBookGenres());
        }
        // Update Tags
        if (Objects.nonNull(book.getBookTags())) {
            bookDB.setBookTags(book.getBookTags());
        }

        return bookRepository.save(bookDB);
    }

    // Delete
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }

}

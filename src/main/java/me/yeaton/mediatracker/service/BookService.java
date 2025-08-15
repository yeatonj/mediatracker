package me.yeaton.mediatracker.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.yeaton.mediatracker.model.Book;
import me.yeaton.mediatracker.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Create
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Read
    public Iterable<Book> fetchBooks() {
        return bookRepository.findAll();
    }

    // Update
    public Book updateBook(Book book, UUID id) {
        Book bookDB = bookRepository.findById(id).get();
        // Update title
        if (Objects.nonNull(book.getTitle()) && !"".equalsIgnoreCase(book.getTitle())) {
            bookDB.setTitle(book.getTitle());
        }
        // Update author
        if (Objects.nonNull(book.getAuthor()) && !"".equalsIgnoreCase(book.getAuthor())) {
            bookDB.setAuthor(book.getAuthor());
        }
        // Update series
        if (Objects.nonNull(book.getSeries()) && !"".equalsIgnoreCase(book.getSeries())) {
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
}

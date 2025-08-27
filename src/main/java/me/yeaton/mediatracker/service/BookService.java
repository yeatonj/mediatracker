package me.yeaton.mediatracker.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import me.yeaton.mediatracker.model.Author;
import me.yeaton.mediatracker.model.Book;
import me.yeaton.mediatracker.model.BookAuthor;
import me.yeaton.mediatracker.model.BookGenre;
import me.yeaton.mediatracker.model.BookTag;
import me.yeaton.mediatracker.model.Genre;
import me.yeaton.mediatracker.model.Tag;
import me.yeaton.mediatracker.model.bookDetails.BookDetail;
import me.yeaton.mediatracker.model.bookDetails.BookOverview;
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

    record IndustryIdentifier(
        String type,
        String identifier
    ) {}

    record ImageLinks(
        String smallThumbnail,
        String thumbnail,
        String small,
        String medium,
        String large,
        String extraLarge
    ) {}

    record VolumeInfo(
        String title,
        String id,
        String[] authors,
        String publisher,
        Integer pageCount,
        String publishedDate,
        String description,
        String[] categories,
        ImageLinks imageLinks,
        IndustryIdentifier[] industryIdentifiers
    ) {}

    public record GoogleBook(
        VolumeInfo volumeInfo
    ) {}

    public record GoogleBookResult(
        GoogleBook[] items
    ) {}

    

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

    // Search for a book/author
    public Iterable<BookOverview> searchBooks(String title, String author) {
        String wildTitle = "%" + title + "%";
        String wildAuthor = "%" + author + "%";
        return bookRepository.searchBooks(wildTitle, wildAuthor);
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

    // Search google books API !! need to add API key
    public GoogleBookResult externalFetchBooks(String title, String author) {
        RestClient client = RestClient.create();

        // Convert terms to lowercase
        String convertedTitle = title.toLowerCase();
        String convertedAuthor = author.toLowerCase();
        String queryString = "intitle:" + convertedTitle + "+inauthor:" + convertedAuthor;

        // Build URI
        UriComponents components = UriComponentsBuilder
            .fromUriString("https://www.googleapis.com/books/v1/volumes")
            .queryParam("q", "{q}")
            .queryParam("printType", "books")
            .encode()
            .build();
        URI finalUri = components.expand(queryString).toUri();

        GoogleBookResult result = client.get()
            .uri(finalUri)
            .retrieve()
            .body(GoogleBookResult.class);

        return result;
    }

    // Create from Google Book !!
    public Book createBookFromGoogleBooks(String id) {
        // Set request information and send
        RestClient client = RestClient.create();
        UriComponents components = UriComponentsBuilder
            .fromUriString("https://www.googleapis.com/books/v1/volumes/{id}")
            .encode()
            .build();

        URI uri = components.expand(id).toUri();
        GoogleBook result = client.get()
            .uri(uri)
            .retrieve()
            .body(GoogleBook.class);
        VolumeInfo info = result.volumeInfo();

        // Create book with base fields
        Book book = new Book(info.title(), info.pageCount(), info.description(), LocalDateTime.parse(info.publishedDate() + "T00:00:00"));
        // Assign industry identifiers
        for (IndustryIdentifier identifier : info.industryIdentifiers()) {
            if (identifier.type().equals("ISBN_13")) {
                book.setIsbnThirteen(identifier.identifier());
            } else if (identifier.type().equals("ISBN_10")) {
                book.setIsbnTen(identifier.identifier());
            }
        }
        // Now, we also need to assign the authors and the genres/tags!
        // Authors
        for (String author : info.authors()) {
            AggregateReference<Author, UUID> ref;
            try {
                ref = AggregateReference.to(authorRepository.findByName(author).get().getId());
                System.out.println("Author Already exists!");
            } catch (Exception e) {
                // Need to add to DB first
                System.out.println("Adding new author!");
                Author authorObj = new Author(author);
                ref = AggregateReference.to(authorRepository.save(authorObj).getId());
            }
            // Add to book authors
            book.addBookAuthor(new BookAuthor(ref));
        }

        // Assume we just have genres, for now (that seems to be what google books provides)
        for (String genre : info.categories()) {
            AggregateReference<Genre, UUID> ref;
            try {
                ref = AggregateReference.to(genreRepository.findByGenre(genre).get().getId());
                System.out.println("Genre Already exists!");
            } catch (Exception e) {
                // Need to add to DB first
                System.out.println("Adding new genre!");
                Genre genreObj = new Genre(genre);
                ref = AggregateReference.to(genreRepository.save(genreObj).getId());
            }
            // Add to book genres
            book.addBookGenre(new BookGenre(ref));
        }
        return bookRepository.save(book);
    }

}

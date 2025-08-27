package me.yeaton.mediatracker.repository;

import java.util.UUID;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import me.yeaton.mediatracker.model.Book;
import me.yeaton.mediatracker.model.bookDetails.BookDetail;

public interface BookRepository extends CrudRepository<Book, UUID>{

    // Get all book details by id
    String bookDetailQuery = "SELECT book.id, book.title, book.series, book.pages, book.description, book.published, book.cover_img_loc AS coverImgLoc, array_agg(DISTINCT genre.genre) AS genres, array_agg(DISTINCT book_genre.id) AS genre_ids, array_agg(DISTINCT tag.tag) AS tags, array_agg(DISTINCT book_tag.tag) AS tag_ids, array_agg(DISTINCT author.name) AS authors, array_agg(DISTINCT book_author.author) AS author_ids\r\n" + //
                "FROM book \r\n" + //
                "LEFT JOIN book_genre ON book.id=book_genre.book\r\n" + //
                "LEFT JOIN genre ON book_genre.genre=genre.id\r\n" + //
                "LEFT JOIN book_tag ON book.id=book_tag.book\r\n" + //
                "LEFT JOIN tag ON book_tag.tag=tag.id\r\n" + //
                "LEFT JOIN book_author ON book.id=book_author.book\r\n" + //
                "LEFT JOIN author ON book_author.author=author.id\r\n" + //
                "WHERE book.id = :id\r\n" + //
                "GROUP BY book.id, book.title, book.series, book.pages, book.description, book.published, book.cover_img_loc;";

    @Query(bookDetailQuery)
    BookDetail findBookDetails(@Param("id") UUID id);
}

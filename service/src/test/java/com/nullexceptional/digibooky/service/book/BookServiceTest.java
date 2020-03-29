package com.nullexceptional.digibooky.service.book;

import com.nullexceptional.digibooky.domain.book.Author;
import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.book.BookRepository;
import com.nullexceptional.digibooky.domain.book.dto.BookDtoDetails;
import com.nullexceptional.digibooky.domain.book.dto.BookDtoGeneral;
import com.nullexceptional.digibooky.domain.book.exceptions.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.UUID;

class BookServiceTest {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private BookService bookService;
    private BookService emptyBookService;
    private Book book1;
    private Book book2;
    private Book book3;
    private BookRepository emptyBookRepository;
    private BookDtoGeneral bookDtoGeneral1;
    private BookDtoGeneral bookDtoGeneral2;
    private BookDtoGeneral bookDtoGeneral3;
    private BookDtoDetails bookDtoDetails1;
    private BookDtoDetails bookDtoDetails2;
    private BookDtoDetails bookDtoDetails3;


    @BeforeEach

    void setUp() {
        bookRepository = new BookRepository();
        emptyBookRepository = new BookRepository();
        bookMapper = new BookMapper();
        bookService = new BookService(bookRepository, bookMapper);
        emptyBookService = new BookService(emptyBookRepository, bookMapper);

        book1 = new Book("9785568123279", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
        book2 = new Book("9781147511154", "The Davinci Code", new Author("Brown", "Dan"), "Blabla summary");
        book3 = new Book("9788758314617", "The Lord Of The Rings", new Author("Tolkien", "JRR"), "Blabla summary");

        bookRepository.getBookCatalog().put(UUID.randomUUID(), book1);
        bookRepository.getBookCatalog().put(UUID.randomUUID(), book2);
        bookRepository.getBookCatalog().put(UUID.randomUUID(), book3);

        bookDtoGeneral1 = bookMapper.fromBookToBookDtoGeneral(book1);
        bookDtoGeneral2 = bookMapper.fromBookToBookDtoGeneral(book2);
        bookDtoGeneral3 = bookMapper.fromBookToBookDtoGeneral(book3);

        bookDtoDetails1 = bookMapper.fromBookToBookDtoDetails(book1);
        bookDtoDetails2 = bookMapper.fromBookToBookDtoDetails(book2);
        bookDtoDetails3 = bookMapper.fromBookToBookDtoDetails(book3);


    }

    @Nested
    class GetAllBooks {
        @Test
        void withNonEmptyRepository() {
            Assertions.assertThat(bookService.getAllBooks()).containsExactlyInAnyOrder(bookDtoGeneral1, bookDtoGeneral2,bookDtoGeneral3);
        }

        @Test
        void withEmptyRepository() {
            Assertions.assertThat(emptyBookService.getAllBooks().size()).isEqualTo(0);
        }
    }

    @Nested
    class GetBookBy{
        @Test
        void correctIsbn() {
            Assertions.assertThat(bookService.getBookByISBN("9785568123279")).isEqualTo(bookDtoDetails1);
            Assertions.assertThat(bookService.getBookByISBN("9781147511154")).isEqualTo(bookDtoDetails2);
        }

        @Test
        void incorrectIsbn() {
            Assertions.assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> bookService.getBookByISBN("invalid isbn"));
        }
    }

    @Nested
    class SearchBookBy{
        @Test
        void isbn_WithWildcard(){
            Assertions.assertThat(bookService.searchBookByISBN("?7811475111*")).containsExactlyInAnyOrder(bookDtoDetails2);
            Assertions.assertThat(bookService.searchBookByISBN("9781147511154")).containsExactlyInAnyOrder(bookDtoDetails2);
            Assertions.assertThat(bookService.searchBookByISBN("97*")).containsExactlyInAnyOrder(bookDtoDetails1,bookDtoDetails2,bookDtoDetails3);
        }

        @Test
        void isbn_WithNoMatch(){
            Assertions.assertThatExceptionOfType(NotFoundException.class).isThrownBy(()->bookService.searchBookByISBN("12322222222?*"));
        }

        @Test
        void authorName_WithWildcard(){
            Assertions.assertThat(bookService.searchBookByAuthorName("tolk?*")).containsExactlyInAnyOrder(bookDtoDetails3);
            Assertions.assertThat(bookService.searchBookByAuthorName("Dan")).containsExactlyInAnyOrder(bookDtoDetails2);
            Assertions.assertThat(bookService.searchBookByAuthorName("J?*")).containsExactlyInAnyOrder(bookDtoDetails1,bookDtoDetails3);
        }

        @Test
        void authorName_WithNoMatch(){
            Assertions.assertThatExceptionOfType(NotFoundException.class).isThrownBy(()->bookService.searchBookByAuthorName("Tim?*"));
        }

        @Test
        void title_WithWilcard(){
            Assertions.assertThat(bookService.searchBookByTitle("L?rd")).containsExactlyInAnyOrder(bookDtoDetails3);
            Assertions.assertThat(bookService.searchBookByTitle("stone")).containsExactlyInAnyOrder(bookDtoDetails1);
            Assertions.assertThat(bookService.searchBookByTitle("d?vinc?")).containsExactlyInAnyOrder(bookDtoDetails2);
        }

        @Test
        void title_WithNoMatch(){
            Assertions.assertThatExceptionOfType(NotFoundException.class).isThrownBy(()->bookService.searchBookByTitle("Tim?*"));
        }
    }
}
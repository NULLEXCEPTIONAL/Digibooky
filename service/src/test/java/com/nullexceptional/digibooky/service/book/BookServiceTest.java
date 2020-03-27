package com.nullexceptional.digibooky.service.book;

import com.nullexceptional.digibooky.domain.book.Author;
import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.book.BookDtoGeneral;
import com.nullexceptional.digibooky.domain.book.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.UUID;

class BookServiceTest {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private BookService bookService;
    private Book book1;
    private Book book2;
    private BookRepository emptyBookRepository;
    private BookDtoGeneral bookDto1;
    private BookDtoGeneral bookDto2;



    @BeforeEach

    void setUp() {
        bookRepository = new BookRepository();
        emptyBookRepository = new BookRepository();
        bookMapper = new BookMapper();
        bookService = new BookService(bookRepository, bookMapper);
        book1 = new Book("123456", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
        book2 = new Book("123456789", "The Davinci Code", new Author("Brown", "Dan"), "Blabla summary");
        bookRepository.getBookCatalog().put(UUID.randomUUID(), book1);
        bookRepository.getBookCatalog().put(UUID.randomUUID(), book2);
        bookDto1 = bookMapper.fromBookToBookDtoGeneral(book1);
        bookDto2 = bookMapper.fromBookToBookDtoGeneral(book2);


    }

    @Nested
    class GetAllBooks {
        @Test
        void withNonEmptyRepository() {
            Assertions.assertThat(bookService.getAllBooks()).containsExactlyInAnyOrder(bookDto1, bookDto2);
        }

        @Test
        void withEmptyRepository() {
            Assertions.assertThat(emptyBookRepository.getAllBooks()).isEqualTo(Arrays.asList());
        }
    }
}
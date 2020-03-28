package com.nullexceptional.digibooky.domain.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

class BookRepositoryTest {

    private Book book1;
    private Book book2;
    private Book book3;
    private BookRepository bookRepository;
    private BookRepository emptyBookRepository;


    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        emptyBookRepository = new BookRepository();

        book1 = new Book("123456", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
        book2 = new Book("123456789", "The Davinci Code", new Author("Brown", "Dan"), "Blabla summary");
        book3 = new Book("1234789", "The Bible", new Author("Christ", "Jesus"), "Blabla summary");
        bookRepository.getBookCatalog().put(UUID.randomUUID(), book1);
        bookRepository.getBookCatalog().put(UUID.randomUUID(), book2);
        bookRepository.getBookCatalog().put(UUID.randomUUID(), book3);
    }

    @Nested
    class GetAllBooks{
        @Test
        void withNonEmptyRepository() {
            Assertions.assertThat(bookRepository.getAllBooks()).containsExactlyInAnyOrder(book1,book2,book3);
        }

        @Test
        void withEmptyRepository() {
            Assertions.assertThat(emptyBookRepository.getAllBooks()).isEqualTo(Arrays.asList());
        }
    }

    @Nested
    class GetBookBy{
        @Test
        void isbn() {
            Assertions.assertThat(bookRepository.getBookByISBN("123456")).isEqualTo(book1);
            Assertions.assertThat(bookRepository.getBookByISBN("123456789")).isEqualTo(book2);

        }
    }


}
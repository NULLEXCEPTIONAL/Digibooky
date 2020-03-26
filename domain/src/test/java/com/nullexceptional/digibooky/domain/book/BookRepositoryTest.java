package com.nullexceptional.digibooky.domain.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

class BookRepositoryTest {

    private Book book1;
    private Book book2;
    private BookRepository bookRepository;


    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        book1 = new Book("123456", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
        book2 = new Book("123456789", "The Davinci Code", new Author("Brown", "Dan"), "Blabla summary");
        bookRepository.getBookCatalog().put(UUID.randomUUID(), book1);
        bookRepository.getBookCatalog().put(UUID.randomUUID(), book2);
    }

    @Test
    void testGetAllBooks() {
        Assertions.assertThat(bookRepository.getAllBooks()).containsExactlyInAnyOrder(book2,book1);
    }
}
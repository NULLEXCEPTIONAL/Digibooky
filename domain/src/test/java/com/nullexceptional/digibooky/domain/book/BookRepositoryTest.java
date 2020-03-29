package com.nullexceptional.digibooky.domain.book;

import com.nullexceptional.digibooky.domain.book.exceptions.NotFoundException;
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
        book3 = new Book("4563258", "The Lord Of The Rings", new Author("Tolkien", "JRR"), "Blabla summary");
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
            Assertions.assertThat(emptyBookRepository.getAllBooks().size()).isEqualTo(0);
        }
    }

    @Nested
    class GetBookBy{
        @Test
        void correctIsbn() {
            Assertions.assertThat(bookRepository.getBookByISBN("123456")).isEqualTo(book1);
            Assertions.assertThat(bookRepository.getBookByISBN("123456789")).isEqualTo(book2);
        }

        @Test
        void incorrectIsbn() {
            Assertions.assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> bookRepository.getBookByISBN("invalid isbn"));
        }
    }

    @Nested
    class SearchBookBy{
        @Test
        void isbn_WithWildcard(){
            Assertions.assertThat(bookRepository.searchBookByISBN("123?*")).containsExactlyInAnyOrder(book1,book2);
            Assertions.assertThat(bookRepository.searchBookByISBN("*345*")).containsExactlyInAnyOrder(book1,book2);
            Assertions.assertThat(bookRepository.searchBookByISBN("*?45*")).containsExactlyInAnyOrder(book1,book2,book3);
        }

        @Test
        void isbn_WithNoMatch(){
            Assertions.assertThatExceptionOfType(NotFoundException.class).isThrownBy(()->bookRepository.searchBookByISBN("12322222222?*"));
        }

        @Test
        void authorName_WithWildcard(){
            Assertions.assertThat(bookRepository.searchBookByAuthor("tolk?*")).containsExactlyInAnyOrder(book3);
            Assertions.assertThat(bookRepository.searchBookByAuthor("Dan")).containsExactlyInAnyOrder(book2);
            Assertions.assertThat(bookRepository.searchBookByAuthor("J?*")).containsExactlyInAnyOrder(book1,book3);
        }

        @Test
        void authorName_WithNoMatch(){
            Assertions.assertThatExceptionOfType(NotFoundException.class).isThrownBy(()->bookRepository.searchBookByAuthor("Tim?*"));
        }

        @Test
        void title_WithWilcard(){
            Assertions.assertThat(bookRepository.searchBookByTitle("L?rd")).containsExactlyInAnyOrder(book3);
            Assertions.assertThat(bookRepository.searchBookByTitle("stone")).containsExactlyInAnyOrder(book1);
            Assertions.assertThat(bookRepository.searchBookByTitle("d?vinc?")).containsExactlyInAnyOrder(book2);
        }
    }




}

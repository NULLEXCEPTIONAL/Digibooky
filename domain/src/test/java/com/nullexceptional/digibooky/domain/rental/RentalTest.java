package com.nullexceptional.digibooky.domain.rental;

import com.nullexceptional.digibooky.domain.book.Author;
import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.members.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class RentalTest {

    private Book book1;
    private User user;
    private Book book2;

    @BeforeEach
    void setUp() {
        book1 = new Book("9785568123279", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
        book2 = new Book("9781147511154", "The Davinci Code", new Author("Brown", "Dan"), "Blabla summary");
        user = new User(UUID.randomUUID(), "465321", "Poppy", "Mommy", "poppy@gmail.com", null);
    }

    @Test
    void createRental_thenRentalHasAnId() {
        // Given
        Rental rental = new Rental(book1,user);
        // When
        UUID actualId = rental.getId();
        // Then
        assertThat(actualId).isNotNull();
    }

    @Test
    void createRental_thenRentalIdMustBeUnique() {
        // Given
        Rental rental1 = new Rental(book1, user);
        Rental rental2 = new Rental(book2, user);
        // When
        UUID idRental1 = rental1.getId();
        UUID idRental2 = rental2.getId();
        // Then
        assertThat(idRental1).isNotEqualTo(idRental2);
    }

    @Test
    void createRental_thenDueDayIsTodayPlus3Weeks() {
        // Given
        Rental rental = new Rental(book1, user);
        // When
        LocalDate actualDueDate = rental.getReturnDate();
        // Then
        assertThat(actualDueDate).isEqualTo(LocalDate.now().plusWeeks(3));
    }

    @Test
    void createRental_thenRentalHasABook() {
        // Given
        Rental rental = new Rental(book1, user);
        // When
        Book actualBook = rental.getBook();
        // Then
        assertThat(actualBook).isEqualTo(book1);
    }

    @Test
    void createRental_thenRentalHasUser() {
        // Given
        Rental rental = new Rental(book2, user);
        // When
        User actualUser = rental.getUser();
        // Then
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    void createRental_thenEndDateIsNull() {
        // Given
        Rental rental = new Rental(book1,user);
        // When
        LocalDate actualEndDate = rental.getEndDate();
        // Then
        assertThat(actualEndDate).isNull();
    }
}
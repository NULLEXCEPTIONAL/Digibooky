package com.nullexceptional.digibooky.domain.rental;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.members.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class RentalTest {

    @Test
    void createRental_thenRentalHasAnId() {
        // Given
        Rental rental = new Rental(null, null);
        // When
        UUID actualId = rental.getId();
        // Then
        assertThat(actualId).isNotNull();
    }

    @Test
    void createRental_thenRentalIdMustBeUnique() {
        // Given
        Rental rental1 = new Rental(null, null);
        Rental rental2 = new Rental(null, null);
        // When
        UUID idRental1 = rental1.getId();
        UUID idRental2 = rental2.getId();
        // Then
        assertThat(idRental1).isNotEqualTo(idRental2);
    }

    @Test
    void createRental_thenDueDayIsTodayPlus3Weeks() {
        // Given
        Rental rental = new Rental(null, null);
        // When
        LocalDate actualDueDate = rental.getReturnDate();
        // Then
        assertThat(actualDueDate).isEqualTo(LocalDate.now().plusWeeks(3));
    }

    @Test
    void createRental_thenRentalHasABook() {
        // Given
        Book expectedBook = new Book(null, null, null, null);
        Rental rental = new Rental(expectedBook, null);
        // When
        Book actualBook = rental.getBook();
        // Then
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    void createRental_thenRentalHasUser() {
        // Given
        User expectedUser = new User(null, null, null, null,null);
        Rental rental = new Rental(null, expectedUser);
        // When
        User actualUser = rental.getUser();
        // Then
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void createRental_thenEndDateIsNull() {
        // Given
        Rental rental = new Rental(null,null);
        // When
        LocalDate actualEndDate = rental.getEndDate();
        // Then
        assertThat(actualEndDate).isNull();
    }
}
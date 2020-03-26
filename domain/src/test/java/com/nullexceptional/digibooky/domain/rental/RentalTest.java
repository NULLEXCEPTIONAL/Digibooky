package com.nullexceptional.digibooky.domain.rental;

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
        assertThat(actualId).isNotEqualTo(null);
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

//    @Test
//    void createRental_thenRentalHasABook() {
//        // Given
//        Book book = new Book();
//        Rental rental = new Rental(book, null);
//        // When
//        Book actualBook = rental.getBook();
//        // Then
//        assertThat(actualBook).isEqualTo(book);
//    }
//
//    @Test
//    void createRental_thenRentalHasUser() {
//        // Given
//        User user = new User();
//        Rental rental = new Rental(null, user);
//        // When
//        User actualUser = rental.getUser();
//        // Then
//        assertThat(actualUser).isEqualTo(user);
//    }
}
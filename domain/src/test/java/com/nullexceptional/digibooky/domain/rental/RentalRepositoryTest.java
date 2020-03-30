package com.nullexceptional.digibooky.domain.rental;

import com.nullexceptional.digibooky.domain.book.Author;
import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.rental.exceptions.RentalIdNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RentalRepositoryTest {

    private RentalRepository rentalRepository;
    private Book book1;
    private Book book2;
    private User user;
    private Book book3;

    @BeforeEach
    void setUp() {
        rentalRepository = new RentalRepository();
        book1 = new Book("9785568123279", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
        book2 = new Book("9781147511154", "The Davinci Code", new Author("Brown", "Dan"), "Blabla summary");
        book3 = new Book("9788758314617", "The Lord Of The Rings", new Author("Tolkien", "JRR"), "Blabla summary");
        user = new User(UUID.randomUUID(), "465321", "Poppy", "Mommy", "poppy@gmail.com", null);
    }

    @Test
    void saveRental_givenARental_thenRepositoryContainsRental() {
        // Given
        Rental rental = new Rental(book1, user);
        // When
        rentalRepository.saveRental(rental);
        // Then
        assertThat(rentalRepository.getRentalsRepo()).containsValue(rental);
    }

    @Test
    void updateEndDateRental_givenARentalId_thenEndDateIsToday() {
        // Given
        Rental rental = new Rental(book1, user);
        rentalRepository.saveRental(rental);
        // When
        rentalRepository.updateEndDateRental(rental.getId());
        // Then
        assertThat(rental.getEndDate().isEqual(LocalDate.now()));
    }

    @Test
    void updateEndDateRental_givenAWrongRentalId_thenThrowRentalIdNotFoundException() {
        // Given
        Rental rental = new Rental(book2, user);
        rentalRepository.saveRental(rental);
        // When
        // Then
        assertThatThrownBy(() -> rentalRepository.updateEndDateRental(UUID.randomUUID()))
                .isInstanceOf(RentalIdNotFoundException.class)
                .hasMessageStartingWith("Log ID:");
    }

    @Test
    void getLentBooksByMember_givenAUserId_thenReturnListOfLentBooksByMember() {
        // Given
        rentalRepository.saveRental(new Rental(book1, user));
        rentalRepository.saveRental(new Rental(book2, new User(UUID.randomUUID(), null, null, null, null, null)));
        rentalRepository.saveRental(new Rental(book3, user));
        // When
        List<Book> lentBooksByMember = rentalRepository.getLentBooksByMember(user.getId());
        // Then
        assertThat(lentBooksByMember).containsExactlyInAnyOrder(book1, book3);
    }

    @Test
    void getAllBooksOverdue_thenReturnListOfBooksWhichAreOverdue() {
        // Given
        Book bookOverdue = book1;
        Book bookOverdueButAlreadyReturned = book2;
        Book bookNotOverdue = book3;
        Rental rental1 = new Rental(bookOverdue, user);
        rental1.setStartDate(LocalDate.now().minusMonths(1));
        Rental rental2 = new Rental(bookNotOverdue, user);
        Rental rental3 = new Rental(bookOverdueButAlreadyReturned, user);
        rental3.setStartDate(LocalDate.now().minusMonths(1));
        rental3.setEndDate(LocalDate.now());
        rentalRepository.saveRental(rental1);
        rentalRepository.saveRental(rental2);
        rentalRepository.saveRental(rental3);
        // When
        List<Book> overdueBooks = rentalRepository.getAllBooksOverdue();
        // Then
        assertThat(overdueBooks).containsExactly(bookOverdue);
    }

    @Test
    void getRental_givenACorrectRentalId_thenReturnRental() {
        // Given
        Rental expectedRental = new Rental(book1, user);
        rentalRepository.saveRental(expectedRental);
        // When
        Rental actualRental = rentalRepository.getRental(expectedRental.getId());
        // Then
        assertThat(actualRental).isEqualTo(expectedRental);
    }

    @Test
    void getRental_givenAWrongRentalId_thenReturnRental() {
        // Given
        Rental expectedRental = new Rental(book1, user);
        rentalRepository.saveRental(expectedRental);
        // When
        // Then
        assertThatThrownBy(() -> rentalRepository.getRental(UUID.randomUUID()))
                .isInstanceOf(RentalIdNotFoundException.class)
                .hasMessageStartingWith("Log ID:");
    }
}
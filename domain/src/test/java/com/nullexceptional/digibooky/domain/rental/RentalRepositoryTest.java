package com.nullexceptional.digibooky.domain.rental;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.rental.exceptions.RentalIdNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.channels.IllegalChannelGroupException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RentalRepositoryTest {

    private RentalRepository rentalRepository;

    @BeforeEach
    void setUp() {
        rentalRepository = new RentalRepository();
    }

   @Test
    void saveRental_givenARental_thenRepositoryContainsRental() {
        // Given
        Rental rental = new Rental(null, null);
        // When
        rentalRepository.saveRental(rental);
        // Then
        assertThat(rentalRepository.getRentalsRepo()).containsValue(rental);
    }

    @Test
    void updateEndDateRental_givenARentalId_thenEndDateIsToday() {
        // Given
        Rental rental = new Rental(null, null);
        rentalRepository.saveRental(rental);
        // When
        rentalRepository.updateEndDateRental(rental.getId());
        // Then
        assertThat(rental.getEndDate().isEqual(LocalDate.now()));
    }

    @Test
    void updateEndDateRental_givenAWrongRentalId_thenThrowRentalIdNotFoundException() {
        // Given
        Rental rental = new Rental(null, null);
        rentalRepository.saveRental(rental);
        // When
        // Then
        assertThatThrownBy(()-> rentalRepository.updateEndDateRental(UUID.randomUUID()))
                .isInstanceOf(RentalIdNotFoundException.class)
                .hasMessageStartingWith("Log ID:");
    }

    @Test
    void getLentBooksByMember_givenAUserId_thenReturnListOfLentBooksByMember() {
        // Given
        User user = new User(UUID.randomUUID(),null,null,null,null,null);
        Book book1 = new Book(null,null,null,null);
        Book book2 = new Book(null,null,null,null);
        Book book3 = new Book(null,null,null,null);
        rentalRepository.saveRental(new Rental(book1,user));
        rentalRepository.saveRental(new Rental(book2,new User(UUID.randomUUID(),null,null,null,null,null)));
        rentalRepository.saveRental(new Rental(book3,user));
        // When
        List<Book> lentBooksByMember = rentalRepository.getLentBooksByMember(user.getId());
        // Then
        assertThat(lentBooksByMember).containsExactlyInAnyOrder(book1,book3);
    }

    @Test
    void getAllBooksOverdue_thenReturnListOfBooksWhichAreOverdue() {
        // Given
        Book bookOverdue = new Book(null,null,null,null);
        Book bookOverdueButAlreadyReturned = new Book(null,null,null,null);
        Book bookNotOverdue = new Book(null,null,null,null);
        Rental rental1 = new Rental(bookOverdue,null);
        rental1.setStartDate(LocalDate.now().minusMonths(1));
        Rental rental2 = new Rental(bookNotOverdue,null);
        Rental rental3 = new Rental(bookOverdueButAlreadyReturned, null);
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
        Rental expectedRental = new Rental(null,null);
        rentalRepository.saveRental(expectedRental);
        // When
        Rental actualRental = rentalRepository.getRental(expectedRental.getId());
        // Then
        assertThat(actualRental).isEqualTo(expectedRental);
    }

    @Test
    void getRental_givenAWrongRentalId_thenReturnRental() {
        // Given
        Rental expectedRental = new Rental(null,null);
        rentalRepository.saveRental(expectedRental);
        // When
        // Then
        assertThatThrownBy(()-> rentalRepository.getRental(UUID.randomUUID()))
                .isInstanceOf(RentalIdNotFoundException.class)
                .hasMessageStartingWith("Log ID:");
    }
}
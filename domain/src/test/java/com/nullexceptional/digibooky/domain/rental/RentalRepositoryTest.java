package com.nullexceptional.digibooky.domain.rental;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.members.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
    void getLentBooksByMember_givenAUserId_thenReturnListOfLentBooksByMember() {
        // Given
        User user = new User(null,null,null,null,null);
        Book book1 = new Book(null,null,null,null);
        Book book2 = new Book(null,null,null,null);
        Book book3 = new Book(null,null,null,null);
        rentalRepository.saveRental(new Rental(book1,user));
        rentalRepository.saveRental(new Rental(book2,new User(null,null,null,null,null)));
        rentalRepository.saveRental(new Rental(book3,user));
        // When
        List<Book> lentBooksByMember = rentalRepository.getLentBooksByMember(user.getId());
        // Then
        assertThat(lentBooksByMember).containsExactlyInAnyOrder(book1,book3);
    }

    @Test
    void getAllBooksOverdue_thenReturnListOfBooksWhichAreOverdue() {
        // Given
        Book overdueBook = new Book(null,null,null,null);
        Book book = new Book(null,null,null,null);
        Rental rental1 = new Rental(overdueBook,null);
        rental1.setStartDate(LocalDate.now().minusMonths(1));
        Rental rental2 = new Rental(book,null);
        rentalRepository.saveRental(rental1);
        rentalRepository.saveRental(rental2);
        // When
        List<Book> overdueBooks = rentalRepository.getAllBooksOverdue();
        // Then
        assertThat(overdueBooks).containsExactly(overdueBook);
    }
}
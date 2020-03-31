package com.nullexceptional.digibooky.service.rental;

import com.nullexceptional.digibooky.domain.book.Author;
import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.book.BookRepository;
import com.nullexceptional.digibooky.domain.book.dto.BookDtoGeneral;
import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.members.UserRepository;
import com.nullexceptional.digibooky.domain.rental.Rental;
import com.nullexceptional.digibooky.domain.rental.RentalRepository;
import com.nullexceptional.digibooky.domain.rental.dto.CreateRentalDto;
import com.nullexceptional.digibooky.domain.rental.dto.RentalDto;
import com.nullexceptional.digibooky.service.book.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RentalServiceIntegrationTest {

    private RentalService rentalService;
    private BookMapper bookMapper;
    private Book book1;
    private Book book2;
    private Book book3;
    private User user;
    private RentalRepository rentalRepository;

    @BeforeEach
    void setUp() {
        BookRepository bookRepository = new BookRepository();
        UserRepository userRepository = new UserRepository();
        RentalMapper rentalMapper = new RentalMapper();
        bookMapper = new BookMapper();
        rentalRepository = new RentalRepository();
        rentalService = new RentalService(rentalRepository, bookRepository, userRepository, rentalMapper,bookMapper);

        book1 = new Book("9785568123279", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
        book2 = new Book("9781147511154", "The Davinci Code", new Author("Brown", "Dan"), "Blabla summary");
        book3 = new Book("9788758314617", "The Lord Of The Rings", new Author("Tolkien", "JRR"), "Blabla summary");

        bookRepository.getBookCatalog().put(UUID.randomUUID(), book1);
        bookRepository.getBookCatalog().put(UUID.randomUUID(), book2);
        bookRepository.getBookCatalog().put(UUID.randomUUID(), book3);

        user = new User(UUID.randomUUID(), "236325", "Flipper", "Flip", "flip@gmail.com", null);
        userRepository.saveUser(user);
    }

    @Test
    void lendBook_thenReturnRentalDto() {
        // Given
        CreateRentalDto createRentalDto = new CreateRentalDto(user.getId(), book1.getIsbn());
        // When
        RentalDto actualRentalDto = rentalService.lendBook(createRentalDto);
        // Then
        assertThat(actualRentalDto.getRentalId()).isNotNull();
        assertThat(actualRentalDto.getBook()).isEqualTo(book1);
        assertThat(actualRentalDto.getUser()).isEqualTo(user);
        assertThat(actualRentalDto.getStartDate()).isEqualTo(LocalDate.now());
    }

    @Test
    void lendBook_givenABookThatIsAlreadyBorrowed_thenThrowIllegalStateException() {
        // Given
        CreateRentalDto createRentalDto = new CreateRentalDto(user.getId(), book1.getIsbn());
        rentalService.lendBook(createRentalDto);
        // When
        // Then
        assertThatThrownBy(()-> rentalService.lendBook(createRentalDto)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void getLentBooksByMember_givenMemberId_thenReturnListOfAllBorrowedBooksByMember() {
        // Given
        rentalRepository.saveRental(new Rental(book1,user));
        rentalRepository.saveRental(new Rental(book2,user));
        rentalRepository.saveRental(new Rental(book3,user));
        // When
        List<BookDtoGeneral> booksLentByMember = rentalService.getLentBooksByMember(user.getId());
        // Then
        assertThat(booksLentByMember).containsExactlyInAnyOrder(
                bookMapper.fromBookToBookDtoGeneral(book1),
                bookMapper.fromBookToBookDtoGeneral(book2),
                bookMapper.fromBookToBookDtoGeneral(book3));
    }

    @Test
    void getAllBooksOverdue_thenReturnListOfAllOverdueBooks() {
        // Given
        Rental rentalOverdue1 = new Rental(book1,user);
        Rental rentalOverdue2 = new Rental(book2,user);
        Rental rentalNotOverdue = new Rental(book3,user);
        rentalOverdue1.setStartDate(LocalDate.now().minusMonths(1));
        rentalOverdue2.setStartDate(LocalDate.now().minusMonths(1));
        rentalRepository.saveRental(rentalOverdue1);
        rentalRepository.saveRental(rentalOverdue2);
        rentalRepository.saveRental(rentalNotOverdue);
        // When
        List<BookDtoGeneral> overdueBooks = rentalService.getAllBooksOverdue();
        // Then
        assertThat(overdueBooks).containsExactlyInAnyOrder(
                bookMapper.fromBookToBookDtoGeneral(book1),
                bookMapper.fromBookToBookDtoGeneral(book2));
    }

    @Test
    void returnBook_whichIsNotOverdue_thenGiveCorrectResponse() {
        // Given
        UUID rentalId = rentalRepository.saveRental(new Rental(book1, user)).getId();
        // When
        String response = rentalService.returnBook(rentalId);
        // Then
        assertThat(response).isEqualTo("Book is returned on time");
    }

    @Test
    void returnBook_whichIsOverdue_thenGiveCorrectResponse() {
        // Given
        Rental rentalOverdue = new Rental(book1, user);
        rentalOverdue.setStartDate(LocalDate.now().minusMonths(1));
        UUID rentalId = rentalRepository.saveRental(rentalOverdue).getId();
        LocalDate returnDate = rentalOverdue.getReturnDate();
        // When
        String response = rentalService.returnBook(rentalId);
        // Then
        assertThat(response).isEqualTo("Book is returned too late, due date was on " + returnDate);
    }
}
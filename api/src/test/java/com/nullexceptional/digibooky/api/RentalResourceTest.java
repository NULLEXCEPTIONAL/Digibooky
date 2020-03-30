package com.nullexceptional.digibooky.api;

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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RentalResourceTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentalRepository rentalRepository;

    private Book book = new Book("9785568123279", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
    private User user = new User(UUID.randomUUID(), "465321", "Poppy", "Mommy", "poppy@gmail.com", null);

    @Test
    void lendBook_withNoAuthorization_thenStatusIsUnAuthorized() {
        // Given
        String url = "/rentals";
        CreateRentalDto createRentalDto = new CreateRentalDto(user.getId(), book.getIsbn());
        // When
        webTestClient
                .post()
                .uri(url)
                .contentType(APPLICATION_JSON)
                .body(Mono.just(createRentalDto), CreateRentalDto.class)
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    void lendBook_withAuthorization_thenStatusIsOkAndRentalDtoIsOk() {
        // Given
        bookRepository.registerNewBook(book);
        userRepository.saveUser(user);
        String url = "/rentals";
        CreateRentalDto createRentalDto = new CreateRentalDto(user.getId(), book.getIsbn());
        // When
        webTestClient
                .post()
                .uri(url)
                .header("Authorization", "Basic " + base64Encode("member:member@gmail.com"))
                .contentType(APPLICATION_JSON)
                .body(Mono.just(createRentalDto), CreateRentalDto.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(RentalDto.class)
                .value(rentalDto -> assertThat(rentalDto.getRentalId()).isNotNull())
                .value(rentalDto -> assertThat(rentalDto.getBook()).isEqualTo(book))
                .value(rentalDto -> assertThat(rentalDto.getUser().getLastName()).isEqualTo(user.getLastName()))
                .value(rentalDto -> assertThat(rentalDto.getStartDate()).isEqualTo(LocalDate.now()))
                .value(rentalDto -> assertThat(rentalDto.getActualReturnDate()).isNull());
    }

    @Test
    void returnBook_withNoAuthorization_thenStatusIsUnAuthorized() {
        // Given
        Rental rental = rentalRepository.saveRental(new Rental(book, user));
        String url = "/rentals/" + rental.getId();
        // When
        webTestClient
                .patch()
                .uri(url)
                .contentType(APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    void returnBook_withAuthorizationAndReturnedOnTime_thenStatusIsOkAndReturnedStringMessageIsOk() {
        // Given
        Rental rental = rentalRepository.saveRental(new Rental(book, user));
        String url = "/rentals/" + rental.getId();
        // When
        webTestClient
                .patch()
                .uri(url)
                .header("Authorization", "Basic " + base64Encode("member:member@gmail.com"))
                .contentType(APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .value(string -> assertThat(string).isEqualTo("Book is returned on time"));
    }

    @Test
    void returnBook_withAuthorizationAndReturnedToLate_thenStatusIsOkAndReturnedStringMessageIsOk() {
        // Given
        Rental rental = rentalRepository.saveRental(new Rental(book, user));
        rental.setStartDate(LocalDate.now().minusMonths(1));
        String url = "/rentals/" + rental.getId();
        // When
        webTestClient
                .patch()
                .uri(url)
                .header("Authorization", "Basic " + base64Encode("member:member@gmail.com"))
                .contentType(APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .value(string -> assertThat(string).isEqualTo("Book is returned to late, due date was on " + rental.getReturnDate()));
    }

    @Test
    void getAllBooksOverdue_withAuthorization_thenStatusIsOkAndReturnedListIsOk() {
        // Given:
        List<BookDtoGeneral> expected = Collections.singletonList(new BookMapper().fromBookToBookDtoGeneral(book));
        Rental rental = new Rental(book, user);
        rental.setStartDate(LocalDate.now().minusMonths(1));
        rentalRepository.saveRental(rental);
        String url = "/rentals";
        // When
        webTestClient
                .get()
                .uri(url)
                .header("Authorization", "Basic " + base64Encode("librarian:librarian@gmail.com"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(BookDtoGeneral.class)
                .isEqualTo(expected);
    }

    @Test
    void getAllBooksOverdue_withNoAuthorization_thenStatusIsUnauthorized() {
        // Given
        String url = "/rentals";
        // When
        webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    void getLentBooksByMember_withNoAuthorization_thenStatusIsUnauthorized() {
        // Given
        String url = "/rentals" + user.getId();
        // When
        webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    void getLentBooksByMember_withAuthorization_thenStatusIsOkAndReturnedListIsOk() {
        // Given
        List<BookDtoGeneral> expected = Collections.singletonList(new BookMapper().fromBookToBookDtoGeneral(book));
        rentalRepository.saveRental(new Rental(book, user));
        String url = "/rentals/" + user.getId();
        // When
        webTestClient
                .get()
                .uri(url)
                .header("Authorization", "Basic " + base64Encode("librarian:librarian@gmail.com"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(BookDtoGeneral.class)
                .isEqualTo(expected);
    }


    private String base64Encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(Charset.defaultCharset()));
    }
}
package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.book.Author;
import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.book.BookRepository;
import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.members.UserRepository;
import com.nullexceptional.digibooky.domain.rental.dto.CreateRentalDto;
import com.nullexceptional.digibooky.domain.rental.dto.RentalDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;

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
    void lendBook_withAuthorization_thenStatusIsOk() {
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
    void name() {
        // Given

        // When

        // Then

    }

    @Test
    void getAllBooksOverdue_withAuthorization_thenStatusIsOk() {
        // Given
        String url = "/rentals";
        // When
        webTestClient
                .get()
                .uri(url)
                .header("Authorization", "Basic " + base64Encode("librarian:librarian@gmail.com"))
                .exchange()
                .expectStatus().isOk();
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
                .expectStatus().isUnauthorized();
    }


    private String base64Encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(Charset.defaultCharset()));
    }
}
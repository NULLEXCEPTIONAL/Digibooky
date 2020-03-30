package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.book.BookRepository;
import com.nullexceptional.digibooky.service.book.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

@SpringBootTest
class BookResourceTest {

    @MockBean
    private BookService bookService;
    private WebTestClient testClient;
    String url;


    @BeforeEach
    void setUp() {
        testClient = WebTestClient.bindToController(new BookResource(bookService)).build();

    }

    @Test
    void checkIfSpringBootIsCorrectlyConfigured() {
        // This test will fail when there are 'obvious' problems with the Spring application context, like a missing bean.
    }

    @Test
//    @WithMockUser(roles = "ADMIN")
    void testBooksPathShouldGiveStatusOK() {
        url = "/books";
        testClient
                .mutateWith(mockUser().roles("Admin"))
                .get()
                .uri(url)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testISBNpathShouldGiveError() {
        url = "/books/isbn/";
        testClient
                .mutateWith(mockUser().roles("Admin"))
                .get()
                .uri(url)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void testTitlePathShouldGiveError() {
        url = "/books/title/";
        testClient
                .mutateWith(mockUser().roles("Admin"))
                .get()
                .uri(url)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void testAuthorPathShouldGiveError() {
        url = "/books/author/";
        testClient
                .mutateWith(mockUser().roles("Admin"))
                .get()
                .uri(url)
                .exchange()
                .expectStatus().is4xxClientError();
    }



}
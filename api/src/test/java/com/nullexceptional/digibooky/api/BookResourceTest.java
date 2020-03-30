package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.book.Author;
import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.book.BookRepository;
import com.nullexceptional.digibooky.domain.book.dto.BookDtoDetails;
import com.nullexceptional.digibooky.domain.book.dto.BookDtoGeneral;
import com.nullexceptional.digibooky.service.book.BookMapper;
import com.nullexceptional.digibooky.service.book.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

@SpringBootTest
class BookResourceTest {

    @MockBean
    private BookResource bookResource;
    private BookService bookService;
    private WebTestClient testClient;
    private BookDtoDetails bookDtoDetails;
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    String url;
    private Book book1;


    @BeforeEach
    void setUp() {
        book1 = new Book("9785568123279", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
        bookRepository = new BookRepository();
        bookMapper = new BookMapper();
        bookService = new BookService(bookRepository,bookMapper);
        bookResource = new BookResource(bookService);
        bookDtoDetails = bookMapper.fromBookToBookDtoDetails(book1);
        testClient = WebTestClient.bindToController(bookResource).build();
    }

    @Test
    void checkIfSpringBootIsCorrectlyConfigured() {
        // This test will fail when there are 'obvious' problems with the Spring application context, like a missing bean.
    }

    @Test
    void testBooksPathShouldGiveStatusOK() {
        url = "/books";
        testClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testISBNpathShouldGiveError() {
        url = "/books/isbn/";
        testClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void testTitlePathShouldGiveError() {
        url = "/books/title/";
        testClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void testAuthorPathShouldGiveError() {
        url = "/books/author/";
        testClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void testRegisterNewBook(){
        url = "/books";
        testClient
                .post()
                .uri(url)
                .body(Mono.just(bookDtoDetails),BookDtoDetails.class)
                .exchange()
                .expectStatus().isOk();
    }





}
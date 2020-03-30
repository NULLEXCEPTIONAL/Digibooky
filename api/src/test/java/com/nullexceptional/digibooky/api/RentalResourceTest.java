package com.nullexceptional.digibooky.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import java.nio.charset.Charset;
import java.util.Base64;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RentalResourceTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @WithAnonymousUser
    void getAllBooksOverdue() {
        // Given
        String url = "/rentals";
        // When
        WebTestClient.ResponseSpec response = this.webTestClient
                .get()
                .uri(url)
                .header("Authorization", "Basic " + base64Encode("librarin:librarian@gmail.com"))
                .exchange();
        // Then
        response.expectStatus().isOk();
    }

    private ExchangeFilterFunction memberCredentials() {
        return basicAuthentication("member","member@gmail.com");
    }

    private ExchangeFilterFunction librarianCredentials() {
        return basicAuthentication("librarian","librarian@gmail.com");
    }

    private ExchangeFilterFunction adminCredentials() {
        return basicAuthentication("admin","admin@gmail.com");
    }

    private String base64Encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(Charset.defaultCharset()));
    }
}
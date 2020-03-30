package com.nullexceptional.digibooky.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.nio.charset.Charset;
import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RentalResourceTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAllBooksOverdue() {
        // Given
        String url = "/rentals";
        // When
        WebTestClient.ResponseSpec response = this.webTestClient
                .get()
                .uri(url)
                .header("Authorization", "Basic " + base64Encode("librarian:librarian@gmail.com"))
                .exchange();
        // Then
        response.expectStatus().isOk();
    }

    private String base64Encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(Charset.defaultCharset()));
    }
}
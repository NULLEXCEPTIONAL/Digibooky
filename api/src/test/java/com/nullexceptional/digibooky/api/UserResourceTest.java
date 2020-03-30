package com.nullexceptional.digibooky.api;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.nio.charset.Charset;
import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserResourceTest {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void whenCreateUser_IfRightCredentials_ReturnUser() {
        //String url="/users";

    }

    @Test
    void whenCreateLibrarian_IfWrongCredential_ReturnError() {
        //String url ="/users/librarian";

    }

    @Test
    void getUserById() {
       // String url="/users/{0}";
    }

    @Test
    void whenGivenWrongCredential_GetNotAuthorizedError() {
        // Given
        String url = "/users";
        // When
        WebTestClient.ResponseSpec response = this.webTestClient
                .get()
                .uri(url)
                .header("Authorization", "Basic " + base64Encode("member:member@gmail.com"))
                .exchange();
        // Then
        response.expectStatus().is4xxClientError();

    }

    private String base64Encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(Charset.defaultCharset()));
    }

}
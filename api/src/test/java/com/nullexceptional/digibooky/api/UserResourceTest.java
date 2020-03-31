package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.members.dto.UserDto;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
        //given
        String jsonBody="{\n" +
                "\t\"lastName\" : \"Mimi\",\n" +
                "\t\"firstName\" : \"kokkkkkkkkko\",\n" +
                "\t\"inss\" : \"123456\",\n" +
                "\t\"email\" : \"uniqueb@gmail.com\",\n" +
                "\t\"address\" : {\n" +
                "\t\t\"streetName\" : \"lolstreet\",\n" +
                "\t\t\"streetNumber\" : 15,\n" +
                "\t\t\"postalCode\" : \"1030\",\n" +
                "\t\t\"city\" : \"Schaerbeek\"\n" +
                "\t}\n" +
                "}";
        String url ="/users/librarian";
        //when
        webTestClient
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + base64Encode("admin:admin@gmail.com"))
                .bodyValue(jsonBody)
                .exchange()
                .expectStatus().isCreated();

    }

    @Test
    void WhenGivenRightCredentials_getUserById() {

        //String url="/users/{0}";

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
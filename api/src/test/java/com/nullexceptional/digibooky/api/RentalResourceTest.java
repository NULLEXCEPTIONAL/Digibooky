//package com.nullexceptional.digibooky.api;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//@SpringBootTest
//class RentalResourceTest {
//
//    @Test
//    void getAllBooksOverdue() {
//        // Given
//        WebTestClient testClient = WebTestClient.bindToController(RentalResource.class).build();
//        String url = "/rentals";
//        // When
//        WebTestClient.ResponseSpec response = testClient
//                .get()
//                .uri(url)
//                .exchange();
//        // Then
//        response.expectStatus().isOk();
//    }
//}
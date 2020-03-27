//package com.nullexceptional.digibooky.api;
//
//import com.nullexceptional.digibooky.DigibookyTestApplication;
//import com.nullexceptional.digibooky.domain.book.Author;
//import com.nullexceptional.digibooky.domain.book.Book;
//import com.nullexceptional.digibooky.domain.book.BookDtoGeneral;
//import com.nullexceptional.digibooky.service.book.BookMapper;
//import io.restassured.RestAssured;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//@SpringBootTest(classes = DigibookyTestApplication.class)
//class BookResourceTest {
//
//
//    BookMapper bookMapper;
//    Book book1;
//    Book book2;
//    BookDtoGeneral bookDto1;
//    BookDtoGeneral bookDto2;
//    List<BookDtoGeneral> bookDtoGeneralList;
//
//    @LocalServerPort
//    int port;
//
//    @BeforeEach
//    void setUp() {
//        RestAssured.port = port;
//        bookMapper = new BookMapper();
//        book1 = new Book("123456", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
//        book2 = new Book("123456789", "The Davinci Code", new Author("Brown", "Dan"), "Blabla summary");
//        bookDto1 = bookMapper.fromBookToBookDtoGeneral(book1);
//        bookDto2 = bookMapper.fromBookToBookDtoGeneral(book2);
//        bookDtoGeneralList = Arrays.asList(bookDto1, bookDto2);
//    }
//
//    @Test
//    void getAllBooks() {
//        RestAssured
//                .when()
//                .get("/")
//                .then().assertThat().body("", Matchers.hasItem(Arrays.asList(bookDto1,bookDto2)));
//    }
//}
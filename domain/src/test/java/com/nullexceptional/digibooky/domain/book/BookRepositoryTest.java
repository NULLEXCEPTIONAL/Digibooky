//package com.nullexceptional.digibooky.domain.book;
//
//import com.nullexceptional.digibooky.api.BookResource;
//import com.nullexceptional.digibooky.service.book.BookMapper;
//import com.nullexceptional.digibooky.service.book.BookService;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//class BookRepositoryTest {
//
//    private Book book1;
//    private Book book2;
//    private BookDtoGeneral bookDtoGeneral1;
//    private BookDtoGeneral bookDtoGeneral2;
//    private BookRepository bookRepository;
//    private BookResource bookResource;
//    private BookMapper bookMapper;
//    private List<BookDtoGeneral> listOfBooks = new ArrayList<>();
//    private BookService bookService = new BookService(bookRepository,bookMapper);
//
//    @BeforeEach
//    void setUp() {
//        bookMapper = new BookMapper();
//        bookResource = new BookResource(bookService);
//        bookRepository = new BookRepository();
//        book1 = new Book("123456", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
//        book2 = new Book("123456789", "The Davinci Code", new Author("Brown", "Dan"), "Blabla summary");
//        bookRepository.getBookCatalog().put(UUID.randomUUID(), book1);
//        bookRepository.getBookCatalog().put(UUID.randomUUID(), book2);
//        bookDtoGeneral1 = bookMapper.fromBookToBookDtoGeneral(book1);
//        bookDtoGeneral2 = bookMapper.fromBookToBookDtoGeneral(book2);
//        listOfBooks.add(bookDtoGeneral1);
//        listOfBooks.add(bookDtoGeneral2);
//
//    }
//
//    @Test
//    void testGetAllBooks_in_BookResource() {
//        Assertions.assertThat(bookResource.getAllBooks()).containsExactlyInAnyOrder((BookDtoGeneral) listOfBooks)
//        ;
//    }
//}
//package com.nullexceptional.digibooky.domain.book;
//
//import java.util.List;
//import java.util.UUID;
//
//public class Main {
//    public static void main(String[] args) {
//        Book book1;
//        Book book2;
//        BookDtoGeneral bookDtoGeneral1;
//        BookDtoGeneral bookDtoGeneral2;
//        BookRepository bookRepository;
//
//        List<BookDtoGeneral> listOfBooks;
//        bookRepository = new BookRepository();
//        book1 = new Book("123456", "The Sorceror's Stone", new Author("Rowlings", "JK"), "Blabla summary");
//        book2 = new Book("123456789", "The Davinci Code", new Author("Brown", "Dan"), "Blabla summary");
//        bookRepository.getBookCatalog().put(UUID.randomUUID(), book1);
//        bookRepository.getBookCatalog().put(UUID.randomUUID(), book2);
//
//    }
//}

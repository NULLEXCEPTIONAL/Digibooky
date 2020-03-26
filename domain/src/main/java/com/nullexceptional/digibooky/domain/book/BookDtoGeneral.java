package com.nullexceptional.digibooky.domain.book;

public class BookDtoGeneral {

    private final String isbn;
    private final String title;
    private final Author author;


    public BookDtoGeneral(String isbn, String title, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;

    }
}

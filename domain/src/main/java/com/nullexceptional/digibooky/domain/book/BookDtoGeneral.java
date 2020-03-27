package com.nullexceptional.digibooky.domain.book;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect
public class BookDtoGeneral {

    private final String isbn;
    private final String title;
    private final Author author;


    public BookDtoGeneral(String isbn, String title, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }


}

package com.nullexceptional.digibooky.domain.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


public class Book {
    private final UUID id;
    private final String isbn;
    private final String title;
    private final Author author;
    private final String summary;
    private boolean isBorrowed = false;
    private boolean isDeleted = false;


    public Book(String isbn, String title, Author author, String summary) {
        this.id = UUID.randomUUID();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;

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

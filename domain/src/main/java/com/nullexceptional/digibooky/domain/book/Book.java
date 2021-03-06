package com.nullexceptional.digibooky.domain.book;

import java.util.Objects;
import java.util.UUID;


public class Book {
    private  UUID id;
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

    public String getSummary() {
        return summary;
    }

    public UUID getId() {
        return id;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public String getAuthorFirstAndLastName() {
        return author.getFirstName() + " " + author.getLastName();
    }

    public void delete() {
        this.isDeleted = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}

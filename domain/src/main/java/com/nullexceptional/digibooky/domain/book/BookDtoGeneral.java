package com.nullexceptional.digibooky.domain.book;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDtoGeneral that = (BookDtoGeneral) o;
        return Objects.equals(isbn, that.isbn) &&
                Objects.equals(title, that.title) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, author);
    }
}

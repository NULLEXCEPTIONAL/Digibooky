package com.nullexceptional.digibooky.domain.book.dto;

import com.nullexceptional.digibooky.domain.book.Author;
import java.util.Objects;

public class BookDtoDetails {
    private final String isbn;
    private final String title;
    private final Author author;
    private final String summary;
    private  String lendingInfo;

    public BookDtoDetails(String isbn, String title, Author author, String summary) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.lendingInfo = "This book is available";
    }

    public void setLendingInfo(String lendingInfo) {
        this.lendingInfo = "Book is borrowed by " + lendingInfo;
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

    public String getLendingInfo() {
        return lendingInfo;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDtoDetails that = (BookDtoDetails) o;
        return Objects.equals(isbn, that.isbn) &&
                Objects.equals(title, that.title) &&
                Objects.equals(author, that.author) &&
                Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, author, summary);
    }
}

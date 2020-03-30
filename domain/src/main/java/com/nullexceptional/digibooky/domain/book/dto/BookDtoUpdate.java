package com.nullexceptional.digibooky.domain.book.dto;

import com.nullexceptional.digibooky.domain.book.Author;

public class BookDtoUpdate {
    private final String title;
    private final Author author;
    private final String summary;

    public BookDtoUpdate(String title, Author author, String summary) {
        this.title = title;
        this.author = author;
        this.summary = summary;
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
}

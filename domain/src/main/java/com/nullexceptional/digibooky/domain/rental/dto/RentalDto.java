package com.nullexceptional.digibooky.domain.rental.dto;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.members.User;

import java.time.LocalDate;

public class RentalDto {

    private final Book book;
    private final User user;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public RentalDto(Book book, User user, LocalDate startDate, LocalDate endDate) {
        this.book = book;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

package com.nullexceptional.digibooky.domain.rental.dto;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.members.User;

import java.time.LocalDate;
import java.util.UUID;

public class RentalDto {

    private final UUID rentalId;
    private final Book book;
    private final User user;
    private final LocalDate startDate;
    private final LocalDate actualReturnDate;

    public RentalDto(UUID rentalId, Book book, User user, LocalDate startDate, LocalDate actualReturnDate) {
        this.rentalId = rentalId;
        this.book = book;
        this.user = user;
        this.startDate = startDate;
        this.actualReturnDate = actualReturnDate;
    }

    public UUID getRentalId() {
        return rentalId;
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

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }
}

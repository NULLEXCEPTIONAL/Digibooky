package com.nullexceptional.digibooky.domain.rental;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.members.User;

import java.time.LocalDate;
import java.util.UUID;

public class Rental {

    public static final int DEFAULT_RENTAL_PERIOD_IN_WEEKS = 3;
    private final UUID id;
    private final Book book;
    private final User user;
    private LocalDate startDate;
    private LocalDate actualReturnDate;

    public Rental(Book book, User user) {
        this.id = UUID.randomUUID();
        this.book = book;
        this.user = user;
        this.startDate = LocalDate.now();
        this.actualReturnDate = null;
    }

    public LocalDate getReturnDate() {
        return startDate.plusWeeks(DEFAULT_RENTAL_PERIOD_IN_WEEKS);
    }

    public UUID getId() {
        return id;
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

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    //created to test getAllBooksOverdue in RentalRepository
    //if someone knows how to do this without creating a setter please tell ;)
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

}

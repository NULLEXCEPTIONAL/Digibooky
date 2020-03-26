package com.nullexceptional.digibooky.domain.rental;

import org.apache.catalina.User;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.UUID;

public class Rental {

    public static final int WEEKS_TO_ADD = 3;
    private final UUID id;
    private final Book book;
    private final User user;
    private final LocalDate rentalDay;

    public Rental(Book book, User user) {
        this.id = UUID.randomUUID();
        this.book = book;
        this.user = user;
        this.rentalDay = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getReturnDate() {
        return rentalDay.plusWeeks(WEEKS_TO_ADD);
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return null;
    }
}

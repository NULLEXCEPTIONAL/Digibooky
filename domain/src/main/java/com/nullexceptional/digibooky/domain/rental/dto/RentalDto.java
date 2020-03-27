package com.nullexceptional.digibooky.domain.rental.dto;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.members.User;

import java.time.LocalDate;

public class RentalDto {

    private Book book;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;

    public RentalDto setEndDate(LocalDate endDate){
        this.endDate = endDate;
        return this;
    }

    public RentalDto setStartDate(LocalDate startDate){
        this.startDate = startDate;
        return this;
    }

    public RentalDto setUser(User user){
        this.user = user;
        return this;
    }

    public RentalDto setBook(Book book){
        this.book = book;
        return this;
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

package com.nullexceptional.digibooky.domain.rental.dto;

import java.util.UUID;

public class CreateRentalDto {

    private final UUID memberId;
    private final String bookISBN;

    public CreateRentalDto(UUID memberId, String bookISBN) {
        this.memberId = memberId;
        this.bookISBN = bookISBN;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public String getBookISBN() {
        return bookISBN;
    }
}

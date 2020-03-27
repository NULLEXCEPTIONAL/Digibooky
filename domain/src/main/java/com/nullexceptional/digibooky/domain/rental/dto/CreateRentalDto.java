package com.nullexceptional.digibooky.domain.rental.dto;

import java.util.UUID;

public class CreateRentalDto {

    private final UUID memberId;
    private final String isbn;

    public CreateRentalDto(UUID memberId, String isbn) {
        this.memberId = memberId;
        this.isbn = isbn;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public String getIsbn() {
        return isbn;
    }
}

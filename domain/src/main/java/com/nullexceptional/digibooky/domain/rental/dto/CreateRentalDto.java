package com.nullexceptional.digibooky.domain.rental.dto;

import java.util.UUID;

public class CreateRentalDto {

    private final UUID userId;
    private final String isbn;

    public CreateRentalDto(UUID userId, String isbn) {
        this.userId = userId;
        this.isbn = isbn;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getIsbn() {
        return isbn;
    }
}

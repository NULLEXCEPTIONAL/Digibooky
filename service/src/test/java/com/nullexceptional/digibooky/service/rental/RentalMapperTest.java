package com.nullexceptional.digibooky.service.rental;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.rental.Rental;
import com.nullexceptional.digibooky.domain.rental.dto.RentalDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class RentalMapperTest {

    private RentalMapper rentalMapper;

    @BeforeEach
    void setUp() {
        rentalMapper = new RentalMapper();
    }

    @Test
    void toDto_givenARental_thenFieldsOfRentalDtoEqualsFieldsOfRental() {
        // Given
        Book expectedBook = new Book(null,null,null,null);
        User expectedUser = new User(null,null,null,null,null,null);
        Rental rental = new Rental(expectedBook, expectedUser);
        // When
        RentalDto rentalDto = rentalMapper.toDto(rental);
        // Then
        assertThat(rentalDto.getBook()).isEqualTo(expectedBook);
        assertThat(rentalDto.getUser()).isEqualTo(expectedUser);
        assertThat(rentalDto.getStartDate()).isEqualTo(LocalDate.now());
        assertThat(rentalDto.getActualReturnDate()).isNull();
    }
}
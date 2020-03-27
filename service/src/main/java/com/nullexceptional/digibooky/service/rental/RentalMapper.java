package com.nullexceptional.digibooky.service.rental;

import com.nullexceptional.digibooky.domain.rental.Rental;
import com.nullexceptional.digibooky.domain.rental.dto.RentalDto;

public class RentalMapper {

    public RentalDto toDto(Rental rental){
        return new RentalDto(
                rental.getBook(),
                rental.getUser(),
                rental.getStartDate(),
                rental.getEndDate()
        );
    }
}

package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.rental.dto.CreateRentalDto;
import com.nullexceptional.digibooky.domain.rental.dto.RentalDto;
import com.nullexceptional.digibooky.service.rental.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/rentals")
public class RentalResource {

    private RentalService rentalService;

    @Autowired
    public RentalResource(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RentalDto lendBook(CreateRentalDto createRentalDto){
        return rentalService.lendBook(createRentalDto);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public String returnBook(){
        return null;
    }


}

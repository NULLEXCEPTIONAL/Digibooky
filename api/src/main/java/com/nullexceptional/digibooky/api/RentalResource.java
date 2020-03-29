package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.book.dto.BookDtoGeneral;
import com.nullexceptional.digibooky.domain.rental.dto.CreateRentalDto;
import com.nullexceptional.digibooky.domain.rental.dto.RentalDto;
import com.nullexceptional.digibooky.service.rental.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public RentalDto lendBook(@RequestBody CreateRentalDto createRentalDto) {
        return rentalService.lendBook(createRentalDto);
    }

    @PatchMapping(path = "/{rentalId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String returnBook(@PathVariable UUID rentalId) {
        return rentalService.returnBook(rentalId);
    }

    @GetMapping(path = "/{memberId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDtoGeneral> getLentBooksByMember(@PathVariable UUID memberId) {
        return rentalService.getLentBooksByMember(memberId);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDtoGeneral> getAllBooksOverdue() {
        return rentalService.getAllBooksOverdue();
    }
}

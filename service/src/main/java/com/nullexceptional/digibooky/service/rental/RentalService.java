package com.nullexceptional.digibooky.service.rental;

import com.nullexceptional.digibooky.domain.book.BookRepository;
import com.nullexceptional.digibooky.domain.rental.RentalRepository;
import com.nullexceptional.digibooky.domain.rental.dto.CreateRentalDto;
import com.nullexceptional.digibooky.domain.rental.dto.RentalDto;
import org.springframework.stereotype.Service;

@Service
public class RentalService {

    private RentalRepository rentalRepository;
    private BookRepository bookRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public RentalDto lendBook(CreateRentalDto createRentalDto) {
        //Book book = waiting for getBookByIsbn in BookRepository
        //User user = waiting for getUserById in UserRepository
        //        return rentalRepository.saveRental(rental);
        return null;
    }

}

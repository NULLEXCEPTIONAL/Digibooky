package com.nullexceptional.digibooky.service.rental;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.book.BookRepository;
import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.members.UserRepository;
import com.nullexceptional.digibooky.domain.rental.Rental;
import com.nullexceptional.digibooky.domain.rental.RentalRepository;
import com.nullexceptional.digibooky.domain.rental.dto.CreateRentalDto;
import com.nullexceptional.digibooky.domain.rental.dto.RentalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RentalMapper rentalMapper;

    @Autowired
    public RentalService(RentalRepository rentalRepository, BookRepository bookRepository, UserRepository userRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.rentalMapper = rentalMapper;
    }

    public RentalDto lendBook(CreateRentalDto createRentalDto) {
        Book book = bookRepository.getBookByISBN(createRentalDto.getIsbn());
        //set Boolean isBorrowed in Book to true
        User member = userRepository.getUserById(createRentalDto.getMemberId());
        return rentalMapper.toDto(rentalRepository.saveRental(new Rental(book, member)));
    }
}

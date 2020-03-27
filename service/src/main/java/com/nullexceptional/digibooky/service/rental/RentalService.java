package com.nullexceptional.digibooky.service.rental;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.book.BookRepository;
import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.members.UserRepository;
import com.nullexceptional.digibooky.domain.rental.Rental;
import com.nullexceptional.digibooky.domain.rental.RentalRepository;
import com.nullexceptional.digibooky.domain.rental.dto.CreateRentalDto;
import com.nullexceptional.digibooky.domain.rental.dto.RentalDto;
import org.springframework.stereotype.Service;


@Service
public class RentalService {

    private RentalRepository rentalRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public RentalDto lendBook(CreateRentalDto createRentalDto) {
        Book book = bookRepository.getBookByISBN(createRentalDto.getIsbn());
        User member = userRepository.getUserById(createRentalDto.getMemberId());
//        return rentalRepository.saveRental(new Rental(book,member));
        return null;
    }

}

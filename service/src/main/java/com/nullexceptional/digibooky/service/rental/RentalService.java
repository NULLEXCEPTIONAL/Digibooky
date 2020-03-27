package com.nullexceptional.digibooky.service.rental;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.book.BookDtoGeneral;
import com.nullexceptional.digibooky.domain.book.BookRepository;
import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.members.UserRepository;
import com.nullexceptional.digibooky.domain.rental.Rental;
import com.nullexceptional.digibooky.domain.rental.RentalRepository;
import com.nullexceptional.digibooky.domain.rental.dto.CreateRentalDto;
import com.nullexceptional.digibooky.domain.rental.dto.RentalDto;
import com.nullexceptional.digibooky.service.book.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RentalMapper rentalMapper;
    private final BookMapper bookMapper;

    @Autowired
    public RentalService(RentalRepository rentalRepository, BookRepository bookRepository, UserRepository userRepository, RentalMapper rentalMapper, BookMapper bookMapper) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.rentalMapper = rentalMapper;
        this.bookMapper = bookMapper;
    }

    public RentalDto lendBook(CreateRentalDto createRentalDto) {
        Book book = bookRepository.getBookByISBN(createRentalDto.getIsbn());
        //set Boolean isBorrowed in Book to true
        User member = userRepository.getUserById(createRentalDto.getMemberId());
        return rentalMapper.toDto(rentalRepository.saveRental(new Rental(book, member)));
    }

    public List<BookDtoGeneral> getLentBooksByMember(UUID userId) {
        return bookMapper.fromBookToBookDtoGeneral(rentalRepository.getLentBooksByMember(userId));
    }

    public List<BookDtoGeneral> getAllBooksOverdue() {
        return bookMapper.fromBookToBookDtoGeneral(rentalRepository.getAllBooksOverdue());
    }
}

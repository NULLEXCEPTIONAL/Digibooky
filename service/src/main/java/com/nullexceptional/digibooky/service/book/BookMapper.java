package com.nullexceptional.digibooky.service.book;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.book.dto.BookDtoDetails;
import com.nullexceptional.digibooky.domain.book.dto.BookDtoGeneral;
import com.nullexceptional.digibooky.domain.rental.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private RentalRepository rentalRepository;

    public BookMapper() {
    }

    @Autowired
    public BookMapper(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public BookDtoGeneral fromBookToBookDtoGeneral(Book book) {
        return new BookDtoGeneral(book.getIsbn(), book.getTitle(), book.getAuthor());
    }

    public List<BookDtoGeneral> fromBookToBookDtoGeneral(List<Book> bookList) {
        return bookList.stream()
                .map(book -> fromBookToBookDtoGeneral(book))
                .collect(Collectors.toList());
    }

    public Book fromBookDtoGeneralToBook(BookDtoDetails bookDtoDetails) {
        return new Book(bookDtoDetails.getIsbn(), bookDtoDetails.getTitle(), bookDtoDetails.getAuthor(), bookDtoDetails.getSummary());
    }

    public BookDtoDetails fromBookToBookDtoDetails(Book book) {
        if (book.isBorrowed()) {
            BookDtoDetails details =  new BookDtoDetails(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getSummary());
            details.setLendingInfo(rentalRepository.getEnhancedBookDetails(book.getId()).getFirstName() + " "
                               +  rentalRepository.getEnhancedBookDetails(book.getId()).getLastName());
            return details;
        }
        return new BookDtoDetails(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getSummary());
    }

    public List<BookDtoDetails> fromBookToBookDtoDetails(List<Book> bookList) {
        return bookList.stream()
                .map(book -> fromBookToBookDtoDetails(book))
                .collect(Collectors.toList());
    }
}

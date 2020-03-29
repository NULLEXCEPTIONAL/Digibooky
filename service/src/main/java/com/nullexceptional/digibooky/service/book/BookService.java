package com.nullexceptional.digibooky.service.book;

import com.nullexceptional.digibooky.domain.book.dto.BookDtoDetails;
import com.nullexceptional.digibooky.domain.book.dto.BookDtoGeneral;
import com.nullexceptional.digibooky.domain.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {
    BookRepository bookRepository;
    BookMapper bookmapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookmapper) {
        this.bookRepository = bookRepository;
        this.bookmapper = bookmapper;
    }

    public List<BookDtoGeneral> getAllBooks(){
        return bookmapper.fromBookToBookDtoGeneral(bookRepository.getAllBooks());
    }

    public void registerNewBook (BookDtoDetails bookToUpdate){
        bookRepository.registerNewBook(bookmapper.fromBookDtoGeneralToBook(bookToUpdate));
    }

    public BookDtoDetails getBookByISBN(String isbn) {
        return bookmapper.fromBookToBookDtoDetails(bookRepository.getBookByISBN(isbn));
    }

    public List<BookDtoDetails> searchBookByISBN(String isbn) {
        return bookmapper.fromBookToBookDtoDetails(bookRepository.searchBookByISBN(isbn));
    }

    public List<BookDtoDetails> searchBookByAuthorName(String authorFullName){
        return bookmapper.fromBookToBookDtoDetails(bookRepository.searchBookByAuthor(authorFullName));
    }

    public List<BookDtoDetails> searchBookByTitle(String titleSearchString){
        return bookmapper.fromBookToBookDtoDetails(bookRepository.searchBookByTitle(titleSearchString));
    }

    public void updateBook (BookDtoDetails bookDtoDetails, String isbn){
        bookRepository.updateBook(bookmapper.fromBookDtoGeneralToBook(bookDtoDetails), isbn);
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteBook(isbn);
    }
}

package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.book.BookDtoDetails;
import com.nullexceptional.digibooky.domain.book.BookDtoGeneral;
import com.nullexceptional.digibooky.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookResource {
    BookService bookService;

    @Autowired
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDtoGeneral> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping(produces = "application/json", path = "isbn/{isbn}")
    public BookDtoDetails getBookByISBN(@PathVariable ("isbn") String isbn){
        return bookService.getBookByISBN(isbn);
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path = "isbn/search")
    public List<BookDtoDetails> searchBookByISBN(@RequestBody String isbn){
        return bookService.searchBookByISBN(isbn);
    }

    @GetMapping(produces = "application/json", path = "title/{title}")
    public List<BookDtoDetails> getBookByTitle(@PathVariable ("title") String titleSearchString){
        return bookService.getBookByTitle(titleSearchString);
    }

    @PostMapping(consumes="application/json")
    public void registerNewBook(@RequestBody BookDtoDetails bookToRegister){
        bookService.registerNewBook(bookToRegister);
    }




}

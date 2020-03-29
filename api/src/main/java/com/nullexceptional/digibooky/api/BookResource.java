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

    @GetMapping(produces = "application/json", path = "isbn/search/{isbn}")
    public List<BookDtoDetails> searchBookByISBN(@PathVariable String isbn){
        return bookService.searchBookByISBN(isbn);
    }

    @GetMapping(produces = "application/json", path = "title/search/{title}")
    public List<BookDtoDetails> searchBookByTitle(@PathVariable ("title") String titleSearchString){
        return bookService.getBookByTitle(titleSearchString);
    }

    @GetMapping(produces = "application/json", path = "author/search/{authorFullName}")
    public List<BookDtoDetails> searchBookByAuthor(@PathVariable ("authorFullName") String authorFullName){
        return bookService.searchBookByAuthorName(authorFullName);
    }

    @PostMapping(consumes="application/json")
    public void registerNewBook(@RequestBody BookDtoDetails bookToRegister){
        bookService.registerNewBook(bookToRegister);
    }

    @PutMapping(consumes="application/json",  path = "{isbn}")
    public void updateBook(@RequestBody BookDtoDetails bookToUpdate, @PathVariable("isbn") String isbn){
        bookService.updateBook(bookToUpdate, isbn);
    }

    @DeleteMapping(path = "{isbn}")
    public void deleteBook(@PathVariable("isbn") String isbn){
        bookService.deleteBook(isbn);
    }




}

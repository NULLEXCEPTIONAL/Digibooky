package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.book.BookDtoGeneral;
import com.nullexceptional.digibooky.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

package com.nullexceptional.digibooky.service.book;

import com.nullexceptional.digibooky.domain.book.Book;
import com.nullexceptional.digibooky.domain.book.BookDtoGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {


    public BookDtoGeneral fromBookToBookDtoGeneral(Book book){
        return new BookDtoGeneral(book.getIsbn(), book.getTitle(), book.getAuthor());
    }

    public List<BookDtoGeneral> fromBookToBookDtoGeneral(List<Book> bookList){
       return bookList.stream()
                .map(book -> fromBookToBookDtoGeneral(book))
                .collect(Collectors.toList());
    }
}
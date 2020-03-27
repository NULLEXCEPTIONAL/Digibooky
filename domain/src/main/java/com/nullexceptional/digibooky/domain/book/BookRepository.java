package com.nullexceptional.digibooky.domain.book;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    Map<UUID, Book> bookCatalog;

    public BookRepository() {
        this.bookCatalog = new ConcurrentHashMap<>();
    }

    public List<Book> getAllBooks(){
        return bookCatalog.values().stream().collect(Collectors.toList());
    }

    public Map<UUID, Book> getBookCatalog() {
        return bookCatalog;
    }

    public void registerNewBook(Book book) {
        //TODO Throw an exception if something goes wrong (Exception-handling?)
        if (!bookInRepository(book)) bookCatalog.put(book.getId(), book);
    }

    private boolean bookInRepository(Book book){
        return bookCatalog.containsKey(book.getId());
    }

    public Book getBookByISBN(String isbn){
        //TODO exception handling for unknown ISBN
        return bookCatalog.values().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findAny()
                .orElse(null);
    }

    public Book getBookByTitle (String titleSearchString) {
        return bookCatalog.values().stream()
                .filter(book -> book.getTitle().matches(".*"+titleSearchString+".*"))
                .findAny()
                .orElse(null);
    }


}

package com.nullexceptional.digibooky.domain.book;

import com.nullexceptional.digibooky.domain.book.exceptions.IsbnNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;
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

    public Book getBookByISBN(String isbn) throws RuntimeException{
            return bookCatalog.values().stream()
                    .filter(book -> book.getIsbn().equals(isbn))
                    .findAny()
                    .orElseThrow(()->new IsbnNotFoundException(isbn));
    }

    public List<Book> searchBookByISBN(String isbn) {
        String newISBN = convertWildCardSymbols(isbn);

        List<Book> result = bookCatalog.values().stream()
                                .filter(book -> book.getIsbn().matches(newISBN))
                                .collect(Collectors.toList());
        if (result.size() == 0) throw new IsbnNotFoundException(isbn);
        return result;
    }

    public List<Book> searchBookByTitle(String titleSearchString) {
        String newText = convertWildCardSymbols(titleSearchString);
        return bookCatalog.values().stream()
                .filter(book -> book.getTitle().matches("(?i:.*" + newText + ".*)"))
                .collect(Collectors.toList());
    }

    public List<Book> searchBookByAuthor(String authorFullName){
        String newName = convertWildCardSymbols(authorFullName);
        return bookCatalog.values().stream()
                .filter(book -> book.getAuthorFirstAndLastName().matches("(?i:.*" + newName + ".*)"))
                .collect(Collectors.toList());
    }

    String convertWildCardSymbols(String text){
        return text
                .replace("*", ".*")
                .replace("?", ".?");
    }

}

package com.nullexceptional.digibooky.domain.book;

import com.nullexceptional.digibooky.domain.IsbnValidator;
import com.nullexceptional.digibooky.domain.book.exceptions.BookAlreadyExistsException;
import com.nullexceptional.digibooky.domain.book.exceptions.InvalidIsbnException;
import com.nullexceptional.digibooky.domain.book.exceptions.NoBookToUpdateException;
import com.nullexceptional.digibooky.domain.book.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    private Map<UUID, Book> bookCatalog;

    public BookRepository() {
        this.bookCatalog = new ConcurrentHashMap<>();
    }

    public List<Book> getAllBooks(){
        return bookCatalog.values().stream()
                .filter((book) -> !book.isDeleted())
                .collect(Collectors.toList());
    }

    public Map<UUID, Book> getBookCatalog() {
        return bookCatalog;
    }

    public void registerNewBook(Book book) throws BookAlreadyExistsException, InvalidIsbnException {
        if (!bookInRepository(book)){
            //TODO Comment out the ISBN validation
            //IsbnValidator.validateIsbn13(book.getIsbn());
            bookCatalog.put(book.getId(), book);
        }
        else throw new BookAlreadyExistsException("Book " + book.getTitle() + " with ISBN " + book.getIsbn() + " is already registered.");

    }

    private boolean bookInRepository(Book book){
        return bookCatalog.containsValue(book);
    }

    public Book getBookByISBN(String isbn) throws RuntimeException{
            return bookCatalog.values().stream()
                    .filter((book) -> !book.isDeleted())
                    .filter(book -> book.getIsbn().equals(isbn))
                    .findAny()
                    .orElseThrow(()->new NotFoundException(isbn));
    }

    public List<Book> searchBookByISBN(String isbn) {
        String newISBN = convertWildCardSymbols(isbn);

        List<Book> result = bookCatalog.values().stream()
                                .filter((book) -> !book.isDeleted())
                                .filter(book -> book.getIsbn().matches(newISBN))
                                .collect(Collectors.toList());
        ifEmptyThrowException(result, isbn);
        return result;
    }

    public List<Book> searchBookByTitle(String titleSearchString) {
        String newText = convertWildCardSymbols(titleSearchString);
        List<Book> result = bookCatalog.values().stream()
                                .filter((book) -> !book.isDeleted())
                                .filter(book -> book.getTitle().matches("(?i:.*" + newText + ".*)"))
                                .collect(Collectors.toList());
        ifEmptyThrowException(result, titleSearchString);
        return result;
    }

    public List<Book> searchBookByAuthor(String authorFullName){
        String newName = convertWildCardSymbols(authorFullName);
        List<Book> result = bookCatalog.values().stream()
                        .filter((book) -> !book.isDeleted())
                        .filter(book -> book.getAuthorFirstAndLastName().matches("(?i:.*" + newName + ".*)"))
                        .collect(Collectors.toList());
        ifEmptyThrowException(result, authorFullName);
        return result;
    }

    private void ifEmptyThrowException(List<Book> result, String searchString) {
        if (result.size() == 0) throw new NotFoundException(searchString);
    }

    String convertWildCardSymbols(String text){
        return text
                .replace("*", ".*")
                .replace("?", ".?");
    }

    public void updateBook(Book updatedBook, String isbn) throws InvalidIsbnException, NoBookToUpdateException {
        Book bookToUpdate = bookCatalog.values().stream()
                            .filter((book) -> book.getIsbn().equals(isbn))
                            .findFirst()
                            .orElseThrow( () -> new NoBookToUpdateException("There is no book with ISBN " + isbn + " to update"));

        //TODO Comment out the ISBN-validation.
        //IsbnValidator.validateIsbn13(updatedBook.getIsbn());
        bookCatalog.put(bookToUpdate.getId(), updatedBook);
    }

    public void deleteBook(String isbn) throws NoBookToUpdateException{
        Book bookToDelete = bookCatalog.values().stream()
                .filter((book) -> book.getIsbn().equals(isbn))
                .filter((book) -> !book.isDeleted())
                .findFirst()
                .orElseThrow( () -> new NoBookToUpdateException("There is no book with ISBN " + isbn + " to delete"));

        bookToDelete.delete();
    }

}

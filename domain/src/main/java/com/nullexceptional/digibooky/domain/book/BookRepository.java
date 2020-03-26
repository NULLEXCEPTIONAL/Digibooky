package com.nullexceptional.digibooky.domain.book;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    Map<UUID, Book> bookCatalog = new ConcurrentHashMap<>();

    public List<Book> getAllBooks(){
        return bookCatalog.values().stream().collect(Collectors.toList());
    }

    public Map<UUID, Book> getBookCatalog() {
        return bookCatalog;
    }
}

package com.__28.books.controller;

import com.__28.books.entity.Books;
import com.__28.books.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BooksAddController {

    @Autowired
    private BooksRepository booksRepository;

    @PostMapping("/add") // Post<<<<데이터 새로만들때 씀
    public Books add(@RequestBody Books book) {
        return booksRepository.save(book);
    }
}
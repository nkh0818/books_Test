package com.__28.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.__28.books.entity.Books;
import com.__28.books.repository.BooksRepository;

@RestController
public class BooksUpdateController {
    @Autowired
    private BooksRepository booksRepository;

    @PutMapping("/update") // Post도 가능하지만 Put << 정보 업데이트같은거 할때 뭐바꿀때 Put으로 씀
    public Books add(@RequestBody Books book) {
        return booksRepository.save(book);
    }
}

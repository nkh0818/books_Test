// BooksSelectController.java
package com.__28.books.controller;

import com.__28.books.entity.Books;
import com.__28.books.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksSelectController {

    @Autowired
    private BooksRepository booksRepository;

    // 전체 조회/ 모든 도서 목록을 가져옵니다.
    @GetMapping("/books")
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    // 1회 조회/ ID(PK)로 특정 도서하나
    @GetMapping("/books/{id}")
    public Books getBookById(@PathVariable Long id) {
        // 있으면 반환 없으면 널
        return booksRepository.findById(id).orElse(null);
    }
}
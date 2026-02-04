// BooksDeleteController.java (새로 생성)
package com.__28.books.controller;

import com.__28.books.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BooksDeleteController {

    @Autowired
    private BooksRepository booksRepository;

    // 삭제/ ID를 경로로 받아 해당 데이터를 삭제합니다.
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        booksRepository.deleteById(id);
        return "삭제 성공: " + id;
    }
}
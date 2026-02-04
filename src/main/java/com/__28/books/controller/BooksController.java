package com.__28.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.__28.books.entity.Books;
import com.__28.books.repository.BooksRepository;

@Controller
public class BooksController {
    @Autowired
    private BooksRepository booksRepository;

    // @GetMapping("/")
    // @ResponseBody // 화면(JSP/HTML) 없이 문자열 그대로 응답시키는거임
    // public String testMethod() {
    // System.out.println("--- 컨트롤러 실제 로직 실행 중 ---");
    // return "테슽!"; // 화면에 테슽이거 나옴댐
    // }

    @GetMapping("/test")
    @ResponseBody
    public String getBooks() {
        return "도서 목록입니다!";
    }

    @GetMapping("/input")
    public String openInputPage() {
        return "bookInput";
    }

    @ResponseBody
    @PostMapping("/")
    public String saveBook(@RequestBody Books book) {
        // 프론트에서 user_Id를 안 보낼 수도 있으니 임시값 설정
        if (book.getUser_Id() == null) {
            book.setUser_Id("admin");
        }

        booksRepository.save(book);
        return "DB 저장 성공! 제목:  " + book.getTitle();
    }
}
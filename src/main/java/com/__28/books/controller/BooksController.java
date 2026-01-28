package com.__28.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BooksController {

    @GetMapping("/")
    @ResponseBody // 화면(JSP/HTML) 없이 문자열 그대로 응답시키는거임
    public String testMethod() {
        System.out.println("--- 컨트롤러 실제 로직 실행 중 ---");
        return "테슽!"; // 화면에 테슽이거 나옴댐
    }
}
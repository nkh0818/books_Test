package com.__28.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BooksController {

    @GetMapping("/test") // 브라우저에서 /test로 접속하면 실행됨
    @ResponseBody // 화면(JSP/HTML) 없이 문자열 그대로 응답
    public String testMethod() {
        System.out.println("--- 컨트롤러 실제 로직 실행 중 ---");
        return "테슽!";
    }
}
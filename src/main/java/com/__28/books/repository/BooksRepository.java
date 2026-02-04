package com.__28.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.__28.books.entity.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {

}

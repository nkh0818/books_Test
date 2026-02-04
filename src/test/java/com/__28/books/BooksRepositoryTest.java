package com.__28.books;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.__28.books.entity.Books;
import com.__28.books.repository.BooksRepository;

@SpringBootTest
public class BooksRepositoryTest {

    @Autowired
    private BooksRepository booksRepository;

    @Test
    public void saveAndFindBookTest() {
        // 데이터 준비
        Books book = new Books();
        book.setUser_Id("user01");
        book.setTitle("제미나이 좋음");
        book.setPrice(15000);

        // When/ (실행)
        Books savedBook = booksRepository.save(book);
        Books foundBook = booksRepository.findById(savedBook.getBooksMain()).orElse(null);

        // Then/ (검증)
        assertThat(foundBook).isNotNull();
        // assertThat(foundBook.getTitle()).isEqualTo("실패");
        assertThat(foundBook.getTitle()).isEqualTo("제미나이 좋음");
        assertThat(foundBook.getUser_Id()).isEqualTo("user01");
        System.out.println("저장된 책 ID: " + foundBook.getBooksMain());
    }
}
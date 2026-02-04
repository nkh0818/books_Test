package com.__28.books;

import com.__28.books.entity.Books;
import com.__28.books.repository.BooksRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // 테스트 완료 후 데이터 롤백!
public class BooksFinalTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long testBookId;

    // 각 테스트 메서드가 실행되기 전에 데이터를 미리 하나 넣어둡니다.
    // @BeforeEach
    // void setUp() {
    // Books book = new Books(null, "tester", "테스트용 책", 12000);
    // Books saved = booksRepository.save(book);
    // testBookId = saved.getBooksMain(); // 생성된 ID 보관
    // }

    @Test
    void 도서_조회_테스트() throws Exception {
        // When/ (GET) /books/{id} 호출
        mockMvc.perform(get("/books/" + testBookId))
                // Then/ 200 OK와 함께 제목이 일치하는지 확인
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("테스트용 책"))
                .andExpect(jsonPath("$.user_Id").value("tester"));
    }

    @Test
    void 도서_삭제_테스트() throws Exception {
        // When/ (삭제)
        mockMvc.perform(delete("/delete/" + testBookId))
                .andExpect(status().isOk())
                .andExpect(content().string("삭제 성공: " + testBookId));

        // Then / 실제로 DB에 없는지 한 번 더 확인
        boolean exists = booksRepository.existsById(testBookId);
        assertThat(exists).isFalse();
    }
}
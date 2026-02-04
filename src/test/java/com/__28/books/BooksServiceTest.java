package com.__28.books;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import com.__28.books.repository.BooksRepository;
import com.__28.books.entity.Books;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // 테스트 후 DB를 깨끗하게 롤백시킵니다.
public class BooksServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private ObjectMapper objectMapper; // JSON 변환용

    // @Test
    // void 등록_후_수정_및_조회_검증_테스트() throws Exception {
    // // 등록 /(Add)
    // Books newBook = new Books(null, "user01", "처음 제목", 10000);
    // String addJson = objectMapper.writeValueAsString(newBook);

    // String response = mockMvc.perform(post("/add")
    // .contentType(MediaType.APPLICATION_JSON)
    // .content(addJson))
    // .andExpect(status().isOk())
    // .andReturn().getResponse().getContentAsString();

    // // 등록된 객체의 ID 추출
    // Books savedBook = objectMapper.readValue(response, Books.class);
    // Long savedId = savedBook.getBooksMain();

    // // 수정 / (Update) - 핵심!
    // savedBook.setTitle("수정된 제목");
    // savedBook.setPrice(20000);
    // String updateJson = objectMapper.writeValueAsString(savedBook);

    // mockMvc.perform(put("/update")
    // .contentType(MediaType.APPLICATION_JSON)
    // .content(updateJson))
    // .andExpect(status().isOk());

    // // 최종 검증 /(Select 후 비교)
    // // 업데이트 명령만 내리고 끝나는 게 아니라, DB에서 진짜 바뀌었는지 다시가저와서 확인시키는것
    // Books updatedBook = booksRepository.findById(savedId).orElseThrow();

    // assertThat(updatedBook.getTitle()).isEqualTo("수정된 제목");
    // assertThat(updatedBook.getPrice()).isEqualTo(20000);
    // System.out.println("검증 완료: " + updatedBook.getTitle());
    // }
}
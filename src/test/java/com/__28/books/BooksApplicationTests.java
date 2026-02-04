package com.__28.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BooksApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	void 테스트_확인() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().string("테슽!"));
	}

	// @Test
	// void 테스트_확인_실패용() throws Exception {
	// mockMvc.perform(get("/"))
	// .andExpect(status().isOk())
	// .andExpect(content().string("테스트 실패!"));
	// }

	// @Test
	// void 테스트_확인_실패용링크() throws Exception {
	// mockMvc.perform(get("/qwe"))
	// .andExpect(status().isOk())
	// .andExpect(content().string("테슽"));
	// }
}

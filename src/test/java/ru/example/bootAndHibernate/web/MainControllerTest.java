package ru.example.bootAndHibernate.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ru.example.bootAndHibernate.controller.MainController;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testHelloResponse() throws Exception {
		mockMvc.perform(get("/hello"))
	        .andExpect(status().isOk())
	        .andExpect(content().string("Hello, Spring Boot!"));
	}
}
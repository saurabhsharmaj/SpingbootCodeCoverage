package com.example.codecoverage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.codecoverage.controller.UserController;
import com.example.codecoverage.modal.User;
import com.example.codecoverage.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class TestUserController {

	@MockBean
	UserService userService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testfindAll() throws Exception {
		User user = new User(1, "Lokesh", "Gupta");
		List<User> users = Arrays.asList(user);

		Mockito.when(userService.listAllUser()).thenReturn(users);

		mockMvc
		.perform(get("/users"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].firstName", Matchers.is("Lokesh")));
	}
}

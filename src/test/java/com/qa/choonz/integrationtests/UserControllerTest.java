package com.qa.choonz.integrationtests;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Users;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class UserControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper;

	@Test
	@Order(1)
	void testCreate() throws Exception {

		Users user = new Users(1L, "Dave", "davealg", "davidalgie");

		String userAsJSON = this.mapper.writeValueAsString(user);

		RequestBuilder mockRequest = post("/users/create").contentType(MediaType.APPLICATION_JSON).content(userAsJSON);

		Users userInDb = new Users(1L, "Dave", "davealg", "davidalgie");

		String userInDbAsJSON = this.mapper.writeValueAsString(userInDb);

		ResultMatcher matchStatus = status().isCreated();

		ResultMatcher matchBody = content().json(userInDbAsJSON);

		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	@Test
	void testReadOneUser() throws Exception {
	
		RequestBuilder mockRequest = get("/users/read/1");
		
		Users userInDb = new Users(1L, "Dave", "davealg", "davidalgie");
		
		String userInDbAsJSON = this.mapper.writeValueAsString(userInDb);
	
		ResultMatcher matchStatus = status().isAccepted();
	
		ResultMatcher matchBody = content().json(userInDbAsJSON);

		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
}
	



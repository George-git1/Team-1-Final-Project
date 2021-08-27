package com.qa.choonz.integrationtests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.rest.dto.ArtistDTO;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ArtistControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper;
	
	

	@Test
	void testCreateArtist() throws Exception {

		Artist artist = new Artist(1L, "Kirk Hammett", null);

		String artistAsJSON = this.mapper.writeValueAsString(artist);

		RequestBuilder mockRequest = post("/artists/create").contentType(MediaType.APPLICATION_JSON)
				.content(artistAsJSON);

		Artist artistInDb = new Artist(2L, "Kirk Hammett", null);

		String artistInDbAsJSON = this.mapper.writeValueAsString(artistInDb);

		ResultMatcher matchStatus = status().isCreated();

		ResultMatcher matchBody = content().json(artistInDbAsJSON);

		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	@Test
	void testReadSingleSuccess() throws Exception{
		ArtistDTO expected = new ArtistDTO(1L, "Kirk Hammett",new ArrayList<Album>());
		String expectedJSON = mapper.writeValueAsString(expected);
		RequestBuilder mockRequest = get("/artists/read/1");
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	



	@Test
	void testDeleteArtist() throws Exception {

		RequestBuilder mockRequest = delete("/artists/delete/1");

		ResultMatcher matchStatus = status().isNoContent();

		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}

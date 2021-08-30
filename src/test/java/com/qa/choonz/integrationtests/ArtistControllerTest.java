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

import com.qa.choonz.persistence.domain.Artist;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:testdata.sql",
		"classpath:testschema.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ArtistControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper;



	@Test
	void testPost() throws Exception {

		Artist artist = new Artist(1L, "Kirk Hammett", null);

		String ToDoAsJSON = this.mapper.writeValueAsString(artist);

		RequestBuilder mockRequest = post("/artists/create").contentType(MediaType.APPLICATION_JSON)
				.content(ToDoAsJSON);

<<<<<<< HEAD
		Artist artistInDb = new Artist(2L, "Kirk Hammett", null);
=======
		Artist savedToDo = new Artist(2L, "Kirk Hammett");
>>>>>>> 11ef748872f3cf65e04010e8909c936c3045bfea

		String savedToDoAsJSON = this.mapper.writeValueAsString(savedToDo);

		ResultMatcher matchStatus = status().isCreated();

		ResultMatcher matchBody = content().json(savedToDoAsJSON);

		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadAllArtists() throws Exception {

		RequestBuilder mockRequest = get("/artists/read");

		Artist artist = new Artist(1L, "Kirk Hammett");

		List<Artist> artistsInDb = new ArrayList<>();
		artistsInDb.add(artist);

		String artistsInDbAsJSON = this.mapper.writeValueAsString(artistsInDb);

		ResultMatcher matchStatus = status().isOk();

		ResultMatcher matchBody = content().json(artistsInDbAsJSON);

		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	public void testReadOne() throws Exception {
		RequestBuilder mockRequest = get("/artists/read/1");

		Artist artistInDb = new Artist(1L, "Kirk Hammett");

		String artistInDbAsJSON = this.mapper.writeValueAsString(artistInDb);

		ResultMatcher matchStatus = status().isOk();

		ResultMatcher matchBody = content().json(artistInDbAsJSON);

		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testUpdateArtist() throws Exception {

		Artist updatedArtist = new Artist("James Hetfield");

		String updatedArtistAsJSON = this.mapper.writeValueAsString(updatedArtist);

		RequestBuilder mockRequest = put("/artists/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(updatedArtistAsJSON);

		Artist updatedArtistInDb = new Artist(1L, "James Hetfield");

		String updatedArtistInDbAsJSON = this.mapper.writeValueAsString(updatedArtistInDb);

		ResultMatcher matchStatus = status().isAccepted();

		ResultMatcher matchBody = content().json(updatedArtistInDbAsJSON);

		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testDeleteArtist() throws Exception {

		RequestBuilder mockRequest = delete("/artists/delete/1");

		ResultMatcher matchStatus = status().isNoContent();

		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}

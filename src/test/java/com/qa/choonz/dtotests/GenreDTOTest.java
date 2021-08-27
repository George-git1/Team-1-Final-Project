package com.qa.choonz.dtotests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.rest.dto.GenreDTO;

public class GenreDTOTest {

	private GenreDTO genreDTO = new GenreDTO(0L, "name", "description", new ArrayList<>());

	@Test
	public void GenreTest() {
		assertThat("GenreDTO [id=0, name=name, description=description, tracks=[]]")
				.isEqualTo(genreDTO.toString());

	}
}

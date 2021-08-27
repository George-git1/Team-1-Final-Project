package com.qa.choonz.dtotests;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.rest.dto.ArtistDTO;

import static org.assertj.core.api.Assertions.assertThat;


public class ArtistDTOTest {

	
	private ArtistDTO artistDTO = new ArtistDTO(0L, "name", new ArrayList<>());
	
	@Test
	public void ArtistTest() {
		assertThat("ArtistDTO [id=0, name=name, albums=[]]").isEqualTo(artistDTO.toString());
		
	}
}

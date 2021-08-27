package com.qa.choonz.dtotests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.rest.dto.PlaylistDTO;

public class PlaylistDTOTest {
	private PlaylistDTO playlistDTO = new PlaylistDTO(0L, "name", "description", "artwork");

	@Test
	public void PlaylistTest() {
	
		assertThat("PlaylistDTO [id=0, name=name, description=description, artwork=artwork, tracks=[]".toString()+"]").isEqualTo(playlistDTO.toString());
}
}

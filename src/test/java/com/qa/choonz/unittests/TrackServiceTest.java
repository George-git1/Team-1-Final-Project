package com.qa.choonz.unittests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.TrackService;

@SpringBootTest
public class TrackServiceTest {

	@MockBean
	private TrackRepository repo;

	@MockBean
	private AlbumRepository albumRepo;

	@Autowired
	private TrackService service;

	
	
	private Artist artist = new Artist(1L, "name", new ArrayList<>());
	
	private Album album = new Album(1L, "name", new ArrayList<>(), artist, "aliens");

	private Track track = new Track(1L, "name", album, new ArrayList<>(), 180, "lyrics");
	
	private TrackDTO trackDTO = new TrackDTO(1L, "name");
	
	private Optional<Track> optionalTrack = Optional.of(new Track(1L, "name", album, new ArrayList<>(), 180, "lyrics"));
	 
	private Track newTrack = new Track(1L, "updated name", album, new ArrayList<>(), 180, "updated lyrics");
	
	private TrackDTO newTrackDTO = new TrackDTO(1L, "updated name");

	@Test
	public void testCreate() {

		Mockito.when(this.repo.save(track)).thenReturn(track);

		assertThat(trackDTO).isEqualTo(this.service.create(track));

		Mockito.verify(this.repo, Mockito.times(1)).save(track);
	}

	@Test
	public void testReadAll() {

		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());

		assertThat(new ArrayList<>()).isEqualTo(this.service.read());

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	public void testReadById() {
		Mockito.when(this.repo.findById(1L)).thenReturn(optionalTrack);

		assertThat(trackDTO).isEqualTo(this.service.read(1L));

		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}

	@Test
	public void testUpdate() {

		Mockito.when(this.repo.findById(1L)).thenReturn(optionalTrack);
		
		Mockito.when(this.repo.save(newTrack)).thenReturn(newTrack);

		assertThat(newTrackDTO).isEqualTo(this.service.update(newTrack, 1L));

		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(newTrack);
	}

	@Test
	public void testDelete() {
		assertThat(true).isEqualTo(this.service.delete(1L));
	}
}
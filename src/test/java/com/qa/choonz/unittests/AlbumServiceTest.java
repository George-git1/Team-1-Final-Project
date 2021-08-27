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
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;

@SpringBootTest
public class AlbumServiceTest {
	@MockBean
	private AlbumRepository repo;

	@MockBean
	private ArtistRepository artistRepo;

	@MockBean
	GenreRepository genreRepo;

	@Autowired
	private AlbumService service;



	private Artist artist = new Artist(1L, "name", new ArrayList<>());

	private Album album = new Album(1L, "name", new ArrayList<>(), artist, "cover");

	private AlbumDTO albumDTO = new AlbumDTO(1L, "name", new ArrayList<>(), artist, "cover");

	private Optional<Album> optionalAlbum = Optional.of(new Album(1L, "name", new ArrayList<>(), artist, "cover"));

	private Album newAlbum = new Album(1L, "updated name", new ArrayList<>(), artist, "updated cover");

	private AlbumDTO newAlbumDTO = new AlbumDTO(1L, "updated name", new ArrayList<>(), artist, "updated cover");

	@Test
	public void testCreate() {

		Mockito.when(this.repo.save(album)).thenReturn(album);

		assertThat(albumDTO).isEqualTo(this.service.create(album));

		Mockito.verify(this.repo, Mockito.times(1)).save(album);
	}

	@Test
	public void testReadAll() {

		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());

		assertThat(new ArrayList<>()).isEqualTo(this.service.read());

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	public void testReadById() {
		Mockito.when(this.repo.findById(1L)).thenReturn(optionalAlbum);

		assertThat(albumDTO).isEqualTo(this.service.read(1L));

		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}

	@Test
	public void testUpdate() {
		Mockito.when(this.repo.findById(1L)).thenReturn(optionalAlbum);

		Mockito.when(this.repo.save(newAlbum)).thenReturn(newAlbum);

		assertThat(newAlbumDTO).isEqualTo(this.service.update(newAlbum, 1L));

		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);

		Mockito.verify(this.repo, Mockito.times(1)).save(newAlbum);
	}

	@Test
	public void testDelete() {
		assertThat(true).isEqualTo(this.service.delete(1L));
	}
}

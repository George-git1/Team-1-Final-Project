package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;

@Service
public class TrackService {

	private TrackRepository repo;
	private AlbumRepository repoAlbum;
    private GenreRepository repoGenre;
	private ModelMapper mapper;

	public TrackService(TrackRepository repo, AlbumRepository repoAlbum, GenreRepository repoGenre, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.repoAlbum = repoAlbum;
		this.repoGenre = repoGenre;
		this.mapper = mapper;
	}

	private TrackDTO mapToDTO(Track track) {
		return this.mapper.map(track, TrackDTO.class);
	}

	public TrackDTO create(Track track) {
		Track created = this.repo.save(track);
		return this.mapToDTO(created);
	}

	public List<TrackDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public TrackDTO read(long id) {
		Track found = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
		return this.mapToDTO(found);
	}

	public TrackDTO update(Track track, long id) {
		Track toUpdate = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
		toUpdate.setName(track.getName());
		toUpdate.setAlbum(track.getAlbum());
		toUpdate.setDuration(track.getDuration());
		toUpdate.setLyrics(track.getLyrics());
		toUpdate.setPlaylist(track.getPlaylist());
		Track updated = this.repo.save(toUpdate);
		return this.mapToDTO(updated);
	}

	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

// album
	public TrackDTO assignAlbum(Long trackId, Long albumId) {

		Track track = repo.findById(trackId).get();
		Album album = repoAlbum.findById(albumId).get();
		track.setAlbum(album);
		Track updated = this.repo.save(track);
		return this.mapToDTO(updated);

	}
	
// genre
	public TrackDTO assignGenre(Long trackId, Long genreId) {
		Track track = repo.findById(trackId).get();
		Genre genre = repoGenre.findById(genreId).get();
		track.setGenre(genre);
		Track updated = this.repo.save(track);
		return this.mapToDTO(updated);
	}

}

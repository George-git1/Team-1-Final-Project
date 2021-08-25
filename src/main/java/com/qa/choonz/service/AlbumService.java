package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.AlbumNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.AlbumDTO;

@Service
public class AlbumService {

    private AlbumRepository repo;
    private ArtistRepository repoArtist;
    private GenreRepository repoGenre;
    private ModelMapper mapper;

    public AlbumService(AlbumRepository repo, ArtistRepository repoArtist, GenreRepository repoGenre, ModelMapper mapper) {
        super();
        this.repoArtist = repoArtist;
        this.repoGenre = repoGenre;
        this.repo = repo;
        this.mapper = mapper;
    }

    private AlbumDTO mapToDTO(Album album) {
        return this.mapper.map(album, AlbumDTO.class);
    }

    public AlbumDTO create(Album album) {
        Album created = this.repo.save(album);
        return this.mapToDTO(created);
    }

    public List<AlbumDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    

    public AlbumDTO read(long id) {
        Album found = this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
        return this.mapToDTO(found);
    }

    public AlbumDTO update(Album album, long id) {
        Album toUpdate = this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
        toUpdate.setName(album.getName());
        toUpdate.setTracks(album.getTracks());
        toUpdate.setArtist(album.getArtist());
        toUpdate.setCover(album.getCover());
        Album updated = this.repo.save(toUpdate);
        return this.mapToDTO(updated);
    }

    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

//Artist
	public AlbumDTO assignArtist(Long albumId, Long artistId) {
		Album album = repo.findById(albumId).get();
		Artist artist = repoArtist.findById(artistId).get();
		album.setArtist(artist);
		Album updated = this.repo.save(album);
		return this.mapToDTO(updated);
	}

// genre

}

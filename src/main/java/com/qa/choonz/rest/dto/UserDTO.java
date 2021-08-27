package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



import com.qa.choonz.persistence.domain.Playlist;


public class UserDTO {

	private Long user_id;
	private String full_name;
	private String user_name;
	private String password;
	private List<Playlist> playlist = new ArrayList<>();

	public UserDTO() {
		super();
	}

	public UserDTO(Long user_id, String full_name, String user_name, String password, List<Playlist> playlist) {
		super();
		this.user_id = user_id;
		this.full_name = full_name;
		this.user_name = user_name;
		this.password = password;
		this.playlist = playlist;
	}
	public UserDTO(Long user_id, String full_name, String user_name, List<Playlist> playlist) {
		super();
		this.user_id = user_id;
		this.full_name = full_name;
		this.user_name = user_name;
		this.playlist = playlist;
	}


	public long getId() {
		return user_id;
	}

	public void setId(Long id) {
		this.user_id = id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Playlist> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<Playlist> playlist) {
		this.playlist = playlist;
	}

	@Override
	public int hashCode() {
		return Objects.hash(full_name, user_id, password, playlist, user_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(full_name, other.full_name) && user_id == other.user_id && Objects.equals(password, other.password)
				&& Objects.equals(playlist, other.playlist) && Objects.equals(user_name, other.user_name);
	}

	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", full_name=" + full_name + ", user_name=" + user_name + ", password=" + password
				+ ", playlist=" + playlist + "]";
	}

}


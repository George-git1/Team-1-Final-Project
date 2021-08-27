package com.qa.choonz.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;



import com.sun.istack.NotNull;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;

	@NotNull
	@Size(max = 100)
	private String full_name;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String user_name;

	@NotNull
	private String password;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Playlist> playlist = new ArrayList<>();

	public Users() {
		super();
	}

	public Users(Long user_id, @NotNull @Size(max = 100) String full_name, @NotNull @Size(max = 100) String user_name, @NotNull String password, List<Playlist> playlist) {
		super();
		this.user_id = user_id;
		this.full_name = full_name;
		this.user_name = user_name;
		this.password = password;
		this.playlist = playlist;
	}
	
	public Users(Long user_id, @NotNull @Size(max = 100) String full_name, @NotNull @Size(max = 100) String user_name, @NotNull String password) {
		super();
		this.user_id = user_id;
		this.full_name = full_name;
		this.user_name = user_name;
		this.password = password;
	}
	public Users( @NotNull @Size(max = 100) String full_name, @NotNull @Size(max = 100) String user_name, @NotNull String password) {
		super();
		
		this.full_name = full_name;
		this.user_name = user_name;
		this.password = password;
	}

	public long getUser_Id() {
		return user_id;
	}

	public void setUser_Id(Long user_id) {
		this.user_id = user_id;
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
		Users other = (Users) obj;
		return Objects.equals(full_name, other.full_name) && user_id == other.user_id && Objects.equals(password, other.password)
				&& Objects.equals(playlist, other.playlist) && Objects.equals(user_name, other.user_name);
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", full_name=" + full_name + ", user_name=" + user_name + ", password=" + password
				+ ", playlist=" + playlist + "]";
	}
	
}


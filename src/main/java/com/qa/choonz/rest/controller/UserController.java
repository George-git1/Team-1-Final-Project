package com.qa.choonz.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.choonz.persistence.domain.Users;

import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}

	// create
	@PostMapping("/create")
	public ResponseEntity<UserDTO> create(@RequestBody Users user) {
		return new ResponseEntity<UserDTO>(this.service.create(user), HttpStatus.CREATED);
	}

	// read all
	@GetMapping("/read")
	public ResponseEntity<List<UserDTO>> read() {
		return new ResponseEntity<List<UserDTO>>(this.service.readAllUsers(), HttpStatus.OK);

	}

	// read by id
	@GetMapping("/read/{id}")
	public ResponseEntity<UserDTO> read(@PathVariable Long user_id) {
		return new ResponseEntity<UserDTO>(this.service.read(user_id), HttpStatus.ACCEPTED);
	}

	//
	@PostMapping("/update/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody Users user, @PathVariable Long user_id) {
		return new ResponseEntity<UserDTO>(this.service.update(user, user_id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Long user_id) {
		return this.service.delete(user_id) ? new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<UserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

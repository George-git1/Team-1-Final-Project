package com.qa.choonz.dtotests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.rest.dto.UserDTO;

@SpringBootTest
public class UserDTOTest {

	
	private UserDTO userDTO = new UserDTO(0L, "full_name", "user_name", "password", new ArrayList<>());
	
	@Test
	public void UserTest() {
		assertThat("UserDTO [id=0, full_name=full_name, user_name=user_name, password=password, playlist=[]]").isEqualTo(userDTO.toString());
		
	}
}

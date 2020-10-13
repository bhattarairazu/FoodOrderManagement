package com.fusemachine.canteenmanagement.FoodOrderingSystem;

import com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity.User;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.Repository.UserRepository;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class FoodOrderingSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void getUserTest(){
		when(userRepository.findAll()).thenReturn(Stream.of(
				new User("rr"),new User("rrr")).collect(Collectors.toList()));
		assertEquals(2,userRepository.findAll().size());
	}

	@Test
	public void SaveUserTest(){
		User user = new User("testingtest");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user,userService.save(user));
	}

	@Test
	public void deleteUserTest(){
		User user = new User("testingtest");
		userRepository.delete(user);
		verify(userRepository,times(1)).delete(user);
	}


}

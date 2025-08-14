package me.yeaton.mediatracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.yeaton.mediatracker.model.UserMain;
import me.yeaton.mediatracker.repository.UserRepository;

@SpringBootTest
class MediatrackerApplicationTests {
	@Autowired
	UserRepository users;

	@Test
	void contextLoads() {
	}

	@Test
	public void createUser() {

		UserMain testUser = new UserMain("dev", "dev@dev.com", "user", "password");
		users.save(testUser);
	}

}

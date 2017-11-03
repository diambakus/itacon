package com.kikia.itacon.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.kikia.itacon.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void whenFindByUsername_thenReturnUser() {
		
		User alfa = new User();
		alfa.setEmail("alfa@test.com");
		alfa.setFirstName("Alfa");
		alfa.setLastName("Beta");
		
		
	}
}

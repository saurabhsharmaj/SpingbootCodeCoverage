package com.example.codecoverage;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.codecoverage.modal.User;
import com.example.codecoverage.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestUserRepository {

	@Autowired
	UserRepository repository;

	@BeforeEach
	void initUseCase() {
		List<User> users = Arrays.asList(new User(101, "saurabh", "sharam"));
		repository.saveAll(users);
	}

	@AfterEach
	public void destroyAll() {
		repository.deleteAll();
	}
	
	@Test
	void findAll_success() {
		 List<User> allCustomer = repository.findAll();
	        assertThat(allCustomer.size()).isGreaterThanOrEqualTo(1);
	}
	
	@Test
	void saveUser_success() {
		List<User> users = Arrays.asList(
				new User(101, "saurabh", "sharam"),
				new User(102, "neha", "sharam"),
				new User(103, "ansh", "sharam"),
				new User(104, "anay", "sharam")
        );
        Iterable<User> allCustomer = repository.saveAll(users);

        AtomicInteger validIdFound = new AtomicInteger();
        allCustomer.forEach(customer -> {
            if(customer.getId()>0){
                validIdFound.getAndIncrement();
            }
        });

        assertThat(validIdFound.intValue()).isEqualTo(3);
	}
	
	@Test
	void deleteUser_success() {
		 repository.deleteById(101);
		 assertThat(repository.count()).isEqualTo(1);
	}
	
	@Test
	void findUser_success() {
		repository.findById(101);
		assertThat(repository.count()).isEqualTo(1);
	}
	
}

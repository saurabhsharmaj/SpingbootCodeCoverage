package com.example.codecoverage;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.codecoverage.modal.User;
import com.example.codecoverage.repository.UserRepository;
import com.example.codecoverage.service.UserService;

@ExtendWith(MockitoExtension.class)
public class TestUserService {

	@Mock
    private UserRepository userRepository;

    UserService userService;
    
    @BeforeEach
    void initUseCase() {
    	//userService = new UserService(userRepository);
    }
    
    @Test
    public void savedUser_Success() {
        User user = new User();
        user.setFirstName("sajedul");
        user.setLastName("karim");

        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getFirstName()).isNotNull();
    }

}

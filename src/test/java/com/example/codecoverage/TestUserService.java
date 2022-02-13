package com.example.codecoverage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.codecoverage.modal.User;
import com.example.codecoverage.repository.UserRepository;
import com.example.codecoverage.service.UserService;

@ExtendWith(MockitoExtension.class)
public class TestUserService {

	@Mock
    private UserRepository userRepository;

	@InjectMocks
    UserService userService;
    
	@Test
	public void listAllUser_Success(){
		List<User> users = new ArrayList<User>();
		users.add(new User(1,"saurabh","shamra"));
		when(userRepository.findAll()).thenReturn(users);
		assertEquals(users, userService.listAllUser());
		assertEquals(1, userService.listAllUser().size());
	}
    
    @Test
    public void savedUser_Success() {
        User user = new User();
        user.setFirstName("sajedul");
        user.setLastName("karim");

        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertEquals(user, savedUser);
    }
    
    @Test
    public void getUser_Success() {
    	User user = new User(1,"saurabh","sharma");
    	when(userRepository.findById(1)).thenReturn(Optional.of(user));
    	User searchUser = userService.getUser(1);
    	assertEquals(user, searchUser);
    }
    
    @Test
    public void deleteUser_Success() {
    	Integer userId = 1;
    	userService.deleteUser(userId);
    	verify(userRepository,times(1)).deleteById(userId);
    }

}

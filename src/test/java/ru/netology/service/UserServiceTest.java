package ru.netology.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import ru.netology.entities.User;
import ru.netology.model.SecurityUser;
import ru.netology.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userDetailsService;
    @Mock
    private UserRepository userRepository;
    private final String USERNAME = "user";
    private final String PASSWORD = "passwd";
    private final User user = new User(USERNAME, PASSWORD, null);
    private final SecurityUser securityUser = new SecurityUser(user);

    @Test
    void loadUserByUsernameTest() {
        given(userRepository.findByUsername(USERNAME)).willReturn(Optional.of(user));
        UserDetails userDetails = userDetailsService.loadUserByUsername(USERNAME);
        assertEquals(securityUser.getPassword(), userDetails.getPassword());
    }
}
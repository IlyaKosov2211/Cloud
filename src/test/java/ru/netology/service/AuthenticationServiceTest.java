package ru.netology.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import ru.netology.dto.AuthenticationRequest;
import ru.netology.jwt.JwtTokenUtil;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService authService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";
    private final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(USERNAME, PASSWORD);
    private final String token = UUID.randomUUID().toString();
    private final AuthenticationRequest authRequest = new AuthenticationRequest(USERNAME, PASSWORD);

    @Test
    void loginUserTest() {
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        given(jwtTokenUtil.generateToken(authentication)).willReturn(token);
        assertEquals(token, authService.loginUser(authRequest));
    }
}
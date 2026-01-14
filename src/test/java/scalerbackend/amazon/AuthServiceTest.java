package com.amazonclone;

import com.amazonclone.dto.LoginRequest;
import com.amazonclone.dto.SignupRequest;
import com.amazonclone.models.Role;
import com.amazonclone.models.User;
import com.amazonclone.repositories.UserRepository;
import com.amazonclone.security.JwtUtil;
import com.amazonclone.services.AuthServiceImpl;
import com.amazonclone.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private CartService cartService;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignupSuccess() {
        SignupRequest req = new SignupRequest("Rajat", "rajat@test.com", "pass");

        when(userRepository.findByEmail(req.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(req.getPassword())).thenReturn("encodedPW");
        when(jwtUtil.generateToken(req.getEmail())).thenReturn("jwtToken");

        var response = authService.signup(req);

        assertEquals("Signup Successful", response.getMessage());
        assertEquals("jwtToken", response.getToken());
        verify(cartService, times(1)).createCart(any());
    }

    @Test
    void testLoginSuccess() {
        LoginRequest req = new LoginRequest("rajat@test.com", "pass");

        User user = User.builder()
                .email(req.getEmail())
                .password("encodedPW")
                .role(Role.USER)
                .build();

        when(userRepository.findByEmail(req.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(req.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(req.getEmail())).thenReturn("jwt123");

        var response = authService.login(req);

        assertEquals("Login Successful", response.getMessage());
        assertEquals("jwt123", response.getToken());
    }
}
    
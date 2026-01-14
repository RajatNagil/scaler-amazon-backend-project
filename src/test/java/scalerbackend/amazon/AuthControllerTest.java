package com.amazonclone;

import com.amazonclone.controllers.AuthController;
import com.amazonclone.dto.*;
import com.amazonclone.services.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    void testSignup() throws Exception {
        MockitoAnnotations.openMocks(this);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        SignupRequest req = new SignupRequest("Rajat", "raj@test.com", "pass123");
        AuthResponse response = new AuthResponse("jwt", "Signup Successful");

        when(authService.signup(Mockito.any())).thenReturn(response);

        mockMvc.perform(
                        post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"fullName\":\"Rajat\",\"email\":\"raj@test.com\",\"password\":\"pass123\"}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Signup Successful"));
    }
}

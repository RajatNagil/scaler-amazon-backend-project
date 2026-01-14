package com.amazonclone.services;

import com.amazonclone.dto.LoginRequest;
import com.amazonclone.dto.SignupRequest;
import com.amazonclone.dto.AuthResponse;

public interface AuthService {
    AuthResponse signup(SignupRequest request);
    AuthResponse login(LoginRequest request);
}

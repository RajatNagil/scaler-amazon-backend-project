package com.amazonclone.services;

import com.amazonclone.dto.UserDto;
import com.amazonclone.models.User;
import com.amazonclone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserFromEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDto getLoggedInUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = getUserFromEmail(email);

        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}

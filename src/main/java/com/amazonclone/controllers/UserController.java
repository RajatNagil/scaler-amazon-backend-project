package com.amazonclone.controllers;

import com.amazonclone.dto.UserDto;
import com.amazonclone.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public UserDto getProfile() {
        return userService.getLoggedInUser();
    }
}

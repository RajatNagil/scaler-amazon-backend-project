package com.amazonclone.services;

import com.amazonclone.dto.UserDto;
import com.amazonclone.models.User;

public interface UserService {
    User getUserFromEmail(String email);
    UserDto getLoggedInUser();
}

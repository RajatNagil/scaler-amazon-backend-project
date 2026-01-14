package com.amazonclone.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequest {
    private String fullName;
    private String email;
    private String password;
}

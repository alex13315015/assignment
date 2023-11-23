package com.ryan9025.ch02.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupDto {
    private String userId;
    private String name;
    private String email;
    private String password;
}

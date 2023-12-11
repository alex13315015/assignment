package com.ryan9025.myhomepage.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinDto {
    @NotBlank
    @Size(min = 4, max = 20)
    private String userID;

    @NotBlank
    @Size(min = 4, max = 20)
    private String password;

    private String name;

    @Email
    private String email;


}

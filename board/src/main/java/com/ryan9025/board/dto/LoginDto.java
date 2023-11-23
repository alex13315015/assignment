package com.ryan9025.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "아이디는 필수 입력사항입니다")
    private String id;

    @NotBlank(message = "패스워드는 필수 입력사항입니다.")
    private String password;
}

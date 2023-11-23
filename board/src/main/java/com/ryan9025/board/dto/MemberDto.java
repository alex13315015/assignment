package com.ryan9025.board.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDto {
    private Integer no;

    @NotBlank
    @Size(min = 3, max = 20)
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 2, max = 10)
    private String name;

    @Email
    private String email;

    private String userRole;

    private Integer status;

    private String regdate;
}

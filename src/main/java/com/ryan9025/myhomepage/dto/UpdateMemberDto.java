package com.ryan9025.myhomepage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateMemberDto {
    private int id;
    private String userID;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String mbti;
    private String description;

}

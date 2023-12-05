package com.ryan9025.jpa.dto;

import com.ryan9025.jpa.entity.Member02;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDto {
    private int id;
    private String userID;
    private String password;
    private String nickName;
    private int age;
    private String email;

    public static MemberDto fromEntity(Member02 member02) {
        return MemberDto.builder()
                .id(member02.getId())
                .userID(member02.getUserID())
                .password(member02.getPassword())
                .nickName(member02.getNickName())
                .age(member02.getAge())
                .email(member02.getEmail())
                .build();
    }
}

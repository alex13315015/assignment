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
    private String userID;
    private String nickName;
    private String gender;
    private int age;
    private String email;
    private String address;

    public static MemberDto fromEntity(Member02 member02) {
        return MemberDto.builder()
                .userID(member02.getUserID())
                .nickName(member02.getNickName())
                .gender(member02.getGender())
                .age(member02.getAge())
                .email(member02.getEmail())
                .build();
    }
}

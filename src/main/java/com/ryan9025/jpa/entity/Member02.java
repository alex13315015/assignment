package com.ryan9025.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member02 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "memberId")
    private Integer id;

    @Column(length = 30)
    private String userID;

    private String nickName;

    private String gender;

    private Integer age;

    private String email;


}

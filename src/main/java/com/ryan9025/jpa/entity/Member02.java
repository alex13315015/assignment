package com.ryan9025.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member02 {

    @Id
    @Column(length = 30, unique = true)
    private String userID;

    @Column(length = 100)
    private String nickName;

    private String gender;

    private Integer age;

    @Column(length = 100)
    private String email;


}

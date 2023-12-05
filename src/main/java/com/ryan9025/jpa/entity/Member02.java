package com.ryan9025.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "memberManager")
public class Member02 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 30, unique = true)
    private String userID;

    @Column(length = 100, nullable = true)
    private String password;

    @Column(length = 100)
    private String nickName;

    private Integer age;

    @Column(length = 100)
    private String email;

    @Column(length = 20, nullable = true)
    private String role;


}

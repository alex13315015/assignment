package com.ryan9025.myhomepage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "web_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private int id;

    @Column(nullable = false, unique = true)
    private String userID;
    @Column(nullable = false)
    private String password;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
}

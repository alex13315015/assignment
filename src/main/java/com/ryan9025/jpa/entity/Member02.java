package com.ryan9025.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
//@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // private으로 생성안되게!
@AllArgsConstructor
@Builder
@DynamicUpdate
@Table(name = "memberManager")
public class Member02 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 100, unique = true)
    private String userID;

    @Column(length = 100, nullable = true)
    private String password;

    @Column(length = 100)
    private String nickName;


    @Column(length = 100)
    private String email;

    @Column(length = 20, nullable = true)
    private String role;


    public void updateMemberInfo(String nickname, String email) {
        this.nickName = nickname;
        this.email = email;
    }
}

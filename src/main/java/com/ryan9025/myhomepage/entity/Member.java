package com.ryan9025.myhomepage.entity;

import com.ryan9025.myhomepage.constant.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // 자동으로 날짜를 생성
@Table(name = "blog_member")
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

    private String phoneNumber;

    private String mbti;

    private String description;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;
}

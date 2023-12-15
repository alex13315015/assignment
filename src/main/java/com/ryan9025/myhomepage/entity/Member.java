package com.ryan9025.myhomepage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ryan9025.myhomepage.constant.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

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

    @JsonIgnore
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

    private String profileImageUrl;

    //양방향 매핑...
    @JsonIgnoreProperties({"member"})
    @OneToMany(mappedBy = "member") // 원래대로라면 column이 만들어지지만 내가 main 테이블이 아니니까 만들지마라!
    private List<Image> imageList;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;
}

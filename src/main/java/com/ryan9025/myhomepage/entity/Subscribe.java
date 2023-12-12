package com.ryan9025.myhomepage.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // 자동으로 날짜를 생성
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "fromMemberID")
    private Member fromMember; //follower

    @ManyToOne
    @JoinColumn(name = "toMemberID")
    private Member toMember; //following

    @CreatedDate
    private LocalDateTime createDate;
}

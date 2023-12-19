package com.ryan9025.myhomepage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String caption;
    private String imageUrl;

    @JoinColumn(name = "member_id")
    @ManyToOne
    @JsonIgnoreProperties({"imageList"})
    private Member member;

    @OneToMany(mappedBy = "image")
    @JsonIgnoreProperties({"image"})
    private List<Likes> likes;

    @OrderBy("createDate DESC , id ASC")
    @OneToMany(mappedBy = "image")
    @JsonIgnoreProperties({"image"})
    private List<Comments> commentsList;

    //좋아요 상태를 나타내기 위해!
    @Transient // 컬럼생성을 막는!!
    private boolean likeState;

    @Transient
    private int likeTotal;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;



}

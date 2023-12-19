package com.ryan9025.myhomepage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(length = 300, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "memberID")
    @JsonIgnoreProperties({"imageList"})
    private Member member;

    @ManyToOne
    @JoinColumn(name = "imageID")
    private Image image;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;
}

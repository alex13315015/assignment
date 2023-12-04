package com.ryan9025.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@SequenceGenerator(
        name = "comment_seq_generator",
        sequenceName = "new_comment_seq",
        initialValue = 1,
        allocationSize = 1
)
@Entity
public class Comment02 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_generator")
    private Integer id;

    @Column(length = 2000)
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Board02 board02;

}

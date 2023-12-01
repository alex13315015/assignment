package com.ryan9025.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "myBoard")
public class Board02 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "boardId")
    private Integer id;

    private String subject;

    @Column(length = 2000)
    private String content;

    @OneToMany(mappedBy = "board02", cascade = CascadeType.REMOVE)
    private List<Comment02> comment02List;

    private LocalDateTime createDate;

}

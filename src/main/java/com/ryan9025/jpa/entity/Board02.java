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
    private Integer id;

    private String subject;

    @Lob
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "board02", cascade = CascadeType.REMOVE)
    private List<Comment02> comment02List;

    @ManyToOne
    @JoinColumn(name = "writer")
    private Member02 writer;

}

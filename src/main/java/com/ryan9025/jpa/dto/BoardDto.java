package com.ryan9025.jpa.dto;

import com.ryan9025.jpa.entity.Board02;
import com.ryan9025.jpa.entity.Comment02;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardDto {
    private int id;
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private List<Comment02> comment02List;

    public static BoardDto fromEntity(Board02 board02) {
        return BoardDto.builder()
                .id(board02.getId())
                .subject(board02.getSubject())
                .content(board02.getContent())
                .createDate(board02.getCreateDate())
                .comment02List(board02.getComment02List())
                .build();
    }
}

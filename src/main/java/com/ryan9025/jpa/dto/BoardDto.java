package com.ryan9025.jpa.dto;

import com.ryan9025.jpa.entity.Board02;
import lombok.*;

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

    public static BoardDto fromEntity(Board02 board02) {
        return BoardDto.builder()
                .subject(board02.getSubject())
                .content(board02.getContent())
                .build();
    }
}

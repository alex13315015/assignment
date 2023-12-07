package com.ryan9025.jpa.dto;

import com.ryan9025.jpa.entity.Board02;
import com.ryan9025.jpa.entity.Comment02;
import com.ryan9025.jpa.entity.Member02;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {
    private Integer id;
    private String content;
    private LocalDateTime createDate;

    private String strCreateDate;
    private Member02 writer;
    private Board02 board02;

    public static CommentDto fromEntity(Comment02 comment02) {
        return CommentDto.builder()
                .id(comment02.getId())
                .content(comment02.getContent())
                .createDate(comment02.getCreateDate())
                .writer(comment02.getWriter())
                //LocalDateTime을 원하는 형식으로 바꿀때..
                .strCreateDate(comment02.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}

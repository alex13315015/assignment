package com.ryan9025.jpa.dto;

import com.ryan9025.jpa.entity.Board02;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class CommentDto {
    private Integer id;
    private String content;
    private LocalDateTime createDate;
}

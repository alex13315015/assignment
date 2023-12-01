package com.ryan9025.jpa.service;

import com.ryan9025.jpa.dto.CommentDto;
import com.ryan9025.jpa.entity.Board02;
import com.ryan9025.jpa.entity.Comment02;
import com.ryan9025.jpa.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public void insertComment(Board02 board02, String content) {
        Comment02 comment02 = Comment02.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .board02(board02)
                .build();
        commentRepository.save(comment02);
    }
}

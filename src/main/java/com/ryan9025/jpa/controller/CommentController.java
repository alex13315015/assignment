package com.ryan9025.jpa.controller;

import com.ryan9025.jpa.dto.BoardDto;
import com.ryan9025.jpa.entity.Board02;
import com.ryan9025.jpa.service.BoardService;
import com.ryan9025.jpa.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/insert/{id}")
    public String insert(@PathVariable("id") int id, @RequestParam String content) {
        BoardDto boardDto = boardService.getBoard(id);
        Board02 board02 = Board02.builder()
                .id(boardDto.getId())
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .createDate(boardDto.getCreateDate())
                .comment02List(boardDto.getComment02List())
                .build();
        commentService.insertComment(board02,content);
        return "redirect:/board/view/" + id;
    }


}

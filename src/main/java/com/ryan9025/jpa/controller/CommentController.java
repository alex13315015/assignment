package com.ryan9025.jpa.controller;

import com.ryan9025.jpa.dto.BoardDto;
import com.ryan9025.jpa.dto.CommentDto;
import com.ryan9025.jpa.entity.Board02;
import com.ryan9025.jpa.entity.Comment02;
import com.ryan9025.jpa.service.BoardService;
import com.ryan9025.jpa.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String,String> delete(@PathVariable("id") int id ) {
        commentService.deleteComment(id);
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("isDelete","OK");
        return resultMap;
    }

    @PostMapping("/ajaxInsert/{id}")
    @ResponseBody
    public Map<String,Object> ajaxInsert(@PathVariable("id") int id, @RequestParam String content) {
        BoardDto boardDto = boardService.getBoard(id);
        Board02 board02 = Board02.builder()
                .id(boardDto.getId())
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .createDate(boardDto.getCreateDate())
                .comment02List(boardDto.getComment02List())
                .build();

        //save된 entity를 리턴받을 수 있다.
        Comment02 comment02 = commentService.ajaxInsertComment(board02,content);
        //데이터를 전달하기 위해 dto로 변환해서
        CommentDto responseComment = CommentDto.fromEntity(comment02);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isInsert","OK");
        //map에 실어서 보낸다.
        resultMap.put("responseComment",responseComment);
        return resultMap;
    }
}

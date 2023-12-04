package com.ryan9025.jpa.controller;


import com.ryan9025.jpa.dto.BoardDto;
import com.ryan9025.jpa.entity.Board02;
import com.ryan9025.jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    @Value("${pagination.size}")
    private int paginationSize;
    private final BoardService boardService;

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/insert")
    public String insert() {
        return "/board/insert";
    }
    @PostMapping("/insert")
    public String insertProcess(BoardDto boardDto) {
        boardService.insertBoard(boardDto);
        return "redirect:/board/list";
    }
    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDto> boardList = boardService.getAllBoard();
        model.addAttribute("boardList",boardList);
        return "/board/list";
    }

    @GetMapping("/pageList")
    public String pageList(Model model, @RequestParam(value = "page", required = true, defaultValue = "0") int page) {
        Page<Board02> pagination = boardService.getAllPageBoard(page);
        log.info("pageBoardList.getTotalPages()==={}",pagination.getTotalPages());
        log.info(pagination.toString());
        List<Board02> boardList = pagination.getContent();
        int start = (int) (Math.floor((double) pagination.getNumber() / paginationSize) * paginationSize);
        int end = start + 5;
        model.addAttribute("start",start);
        model.addAttribute("end",end);
        model.addAttribute("boardList",boardList);
        model.addAttribute("pagination",pagination);
        return "/board/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable int id, Model model) {
        log.info("id==={}",id);
        BoardDto board = boardService.getBoard(id);
        log.info("commentList==={}",board.getComment02List().size());
        model.addAttribute("board",board);
        return "/board/view";
    }
}

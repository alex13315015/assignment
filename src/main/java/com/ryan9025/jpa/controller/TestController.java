package com.ryan9025.jpa.controller;


import com.ryan9025.jpa.entity.Board02;
import com.ryan9025.jpa.entity.Member02;
import com.ryan9025.jpa.repository.BoardRepository;
import com.ryan9025.jpa.repository.MemberRepository;
import com.ryan9025.jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/insert")
    public String insert() {
        return "/insert";
    }

    @PostMapping("/insert")
    public String insertProcess(@ModelAttribute Board02 board02) {
        Board02 dbInsertBoard = Board02.builder()
                .subject(board02.getSubject())
                .content(board02.getContent())
                .build();
        boardService.insertBoard(dbInsertBoard);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Board02> boardList = boardService.getAllBoard();
        model.addAttribute("boardList",boardList);
        return "/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable int id, Model model) {
        log.info("id==={}",id);
        Board02 board = boardService.getBoard(id);
        model.addAttribute("board",board);
        return "/view";
    }

    /*@GetMapping("/member")
    @ResponseBody
    public String myPage() {
        for(int i = 0; i < 100; i++) {
            Member02 member02 = Member02.builder()
                    .userID("hyuk9402")
                    .name("손승혁")
                    .age(30)
                    .email("hyuk9402@naver.com")
                    .build();
            memberRepository.save(member02);
        }
        return "myPage";
    }*/
}

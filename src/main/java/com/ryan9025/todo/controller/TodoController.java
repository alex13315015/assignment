package com.ryan9025.todo.controller;

import com.ryan9025.todo.dto.TodoDto;
import com.ryan9025.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping({"/","/index"})
    public String index() {
        return "/todo/index";
    }

    @PostMapping("/insert")
    public String insertTodo(@ModelAttribute TodoDto todoDto) {
        todoService.insertTodo(todoDto);
        return "/todo/index";
    }

}

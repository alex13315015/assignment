package com.ryan9025.todo.controller;

import com.ryan9025.todo.dto.TodoDto;
import com.ryan9025.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping({"/","/index"})
    public String index() {
        return "/todo/index";
    }

    @PostMapping("/insert")
    @ResponseBody
    public List<TodoDto> insertTodo(@ModelAttribute TodoDto todoDto) {
        todoService.insertTodo(todoDto);
        List<TodoDto> todoList = todoService.getPickedDateTodo(todoDto);
        return todoList;
    }

    @PostMapping("/list")
    @ResponseBody
    public List<TodoDto> getPickedTodo(@ModelAttribute TodoDto todoDto) {
        List<TodoDto> todoList = todoService.getPickedDateTodo(todoDto);
        return todoList;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public List<TodoDto> deleteTodo(@ModelAttribute TodoDto todoDto) {
        List<TodoDto> todoList = todoService.deleteTodo(todoDto);
        /*Map<String,Integer> resultMap = new HashMap<>();
        resultMap.put("isDelete",result);*/
        return todoList;
    }

    @PutMapping("/update")
    @ResponseBody
    public List<TodoDto> updateTodo(@ModelAttribute TodoDto todoDto) {
        List<TodoDto> todoList = todoService.updateTodo(todoDto);
        return todoList;
    }
}
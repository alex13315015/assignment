package com.ryan9025.todo.controller;

import com.ryan9025.todo.dto.CalendarDto;
import com.ryan9025.todo.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("/")
    public String calendar (Model model) {return "";
    }

    @GetMapping("/form")
    public String calendarForm(Model model) {
        return "/todo/calendarForm";
    }

    @PostMapping("/todo")
    public String calendarTodo(@ModelAttribute CalendarDto calendarDto) {
        int result = calendarService.calendarTodo(calendarDto);
        return "/todo/calendar";

    }


}

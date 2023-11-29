package com.ryan9025.todo.service;

import com.ryan9025.todo.dao.CalendarDao;
import com.ryan9025.todo.dto.CalendarDto;
import com.ryan9025.todo.dto.TodoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarDao calendarDao;
    public int calendarTodo(CalendarDto calendarDto) {
        int result = calendarDao.calendarTodo(calendarDto);
        return result;
    }

}

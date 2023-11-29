package com.ryan9025.todo.dao;

import com.ryan9025.todo.dto.CalendarDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarDao {
    int calendarTodo(CalendarDto calendarDto);
}

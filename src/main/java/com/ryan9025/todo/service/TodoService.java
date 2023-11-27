package com.ryan9025.todo.service;

import com.ryan9025.todo.dao.TodoDao;
import com.ryan9025.todo.dto.TodoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoDao todoDao;

    public int insertTodo(TodoDto todoDto) {
        int result = todoDao.insertTodo(todoDto);
        return result;
    }
    public List<TodoDto> getPickedDateTodo(TodoDto todoDto) {
        List<TodoDto> todoList = todoDao.getPickedDateTodo(todoDto);
        return todoList;
    }
}

package com.ryan9025.todo.service;

import com.ryan9025.todo.dao.TodoDao;
import com.ryan9025.todo.dto.TodoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoDao todoDao;

    public int insertTodo(TodoDto todoDto) {
        int result = todoDao.insertTodo(todoDto);
        return result;
    }
    //해당하는 날짜의 일을 모두 들고오는 !
    public List<TodoDto> getPickedDateTodo(TodoDto todoDto) {
        List<TodoDto> todoList = todoDao.getPickedDateTodo(todoDto);
        return todoList;
    }
    @Transactional
    public List<TodoDto> deleteTodo(TodoDto todoDto) {
        int result = todoDao.deleteTodo(todoDto);
        List<TodoDto> todoList = todoDao.getPickedDateTodo(todoDto);
        return todoList;
        //return result;
    }
    @Transactional
    public List<TodoDto> updateTodo(TodoDto todoDto) {
        int result = todoDao.updateTodo(todoDto);
        List<TodoDto> todoList = todoDao.getPickedDateTodo(todoDto);
        return todoList;
    }

    public List<TodoDto> getDateCount() {
        List<TodoDto> todoList = todoDao.getDateCount();
        return todoList;
    }

}

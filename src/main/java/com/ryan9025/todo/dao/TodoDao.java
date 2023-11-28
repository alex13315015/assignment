package com.ryan9025.todo.dao;

import com.ryan9025.todo.dto.TodoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoDao {
    int insertTodo(TodoDto todoDto);

    List<TodoDto> getPickedDateTodo(TodoDto todoDto);

    int deleteTodo(TodoDto todoDto);

    int updateTodo(TodoDto todoDto);

}

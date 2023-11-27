package com.ryan9025.todo.dao;

import com.ryan9025.todo.dto.TodoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoDao {
    int insertTodo(TodoDto todoDto);
}

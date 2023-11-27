package com.ryan9025.todo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TodoDto {
    private int no;
    private String todo;
    private String pickedDate;
    private String isDone;
}

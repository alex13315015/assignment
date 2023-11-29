package com.ryan9025.todo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL) // json변환할때 null을 배제해야겠다..
public class TodoDto {
    private int no;
    private String todo;
    private String pickedDate;
    private String isDone;
    private int count;
}

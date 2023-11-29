package com.ryan9025.todo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalendarDto {
    private String id;
    private String title;
    private String startDate;
    private String endDate;
    private String backgroundColor;
}

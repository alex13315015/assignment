package com.ryan9025.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardDto {
    //NotNull null허용 금지   NotEmpty  NotNull+""  NotBlank NotEmpty "   "
    private int id;

    @NotEmpty(message = "이름은 필수 입력사항입니다.")
    private String name;

    @NotBlank(message = "제목은 필수 입력사항입니다.")
    @Size(min=5,max=100, message = "최소5글자 이상 최대 100자까지 가능합니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력사항입니다.")
    @Size(min=10,max=1000, message = "최소10글자 이상 최대 1000자까지 가능합니다.")
    private String content;

    private String regdate;
}

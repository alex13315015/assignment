package com.ryan9025.board.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MailDto {
    private String receiver;
    private String title;
    private String content;

}

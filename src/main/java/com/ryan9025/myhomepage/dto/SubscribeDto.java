package com.ryan9025.myhomepage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SubscribeDto {
    private Integer ID;
    private String profileImageUrl;
    private String name;
    private Character subscribeState;
    private Character equalState;
}

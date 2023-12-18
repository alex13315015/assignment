package com.ryan9025.myhomepage.dto;

import com.ryan9025.myhomepage.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberProfileDto {
    private boolean pageOwner;
    private Member member;
    private int subscribeCount;
    private boolean subscribeState;
    private int likeTotal;
}

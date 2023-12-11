package com.ryan9025.myhomepage.service;

import com.ryan9025.myhomepage.constant.Role;
import com.ryan9025.myhomepage.dto.JoinDto;
import com.ryan9025.myhomepage.entity.Member;
import com.ryan9025.myhomepage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public Member join(JoinDto joinDto) {

        Member dbJoinMember = Member.builder()
                .userID(joinDto.getUserID())
                .password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                .role(Role.ROLE_USER)
                .email(joinDto.getEmail())
                .name(joinDto.getName())
                .build();
        return memberRepository.save(dbJoinMember);
    }
}

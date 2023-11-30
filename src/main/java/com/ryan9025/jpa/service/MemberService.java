package com.ryan9025.jpa.service;

import com.ryan9025.jpa.entity.Member02;
import com.ryan9025.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member02 joinMember(Member02 member02) {
        Member02 join = memberRepository.save(member02);
        return join;
    }


    public List<Member02> getAllMember() {
        List<Member02> memberList = memberRepository.findAll();
        return memberList;
    }
}

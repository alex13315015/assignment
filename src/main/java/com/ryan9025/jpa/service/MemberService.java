package com.ryan9025.jpa.service;

import com.ryan9025.jpa.dto.MemberDto;
import com.ryan9025.jpa.entity.Member02;
import com.ryan9025.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDto joinMember(MemberDto memberDto) {
        Member02 dbJoinMember = Member02.builder()
                .userID(memberDto.getUserID())
                .nickName(memberDto.getNickName())
                .gender(memberDto.getGender())
                .age(memberDto.getAge())
                .email(memberDto.getEmail())
                .build();
        Member02 responseMember = memberRepository.save(dbJoinMember);
        MemberDto responseMemberDto = MemberDto.fromEntity(responseMember);
        return responseMemberDto;
    }


    public List<MemberDto> getAllMember() {
        // stream 과 lamda 를 쓰면 이걸 간단히 쓸수있다!!
        List<Member02> member02List = memberRepository.findAll();
        List<MemberDto> memberList = new ArrayList<>();
        for(int i = 0; i < member02List.size(); i++) {
            memberList.add(MemberDto.fromEntity(member02List.get(i)));
        }
        return memberList;
    }
}

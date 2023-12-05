package com.ryan9025.jpa.service;

import com.ryan9025.jpa.dto.MemberDto;
import com.ryan9025.jpa.entity.Member02;
import com.ryan9025.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDto joinMember(MemberDto memberDto) {
        Member02 dbJoinMember = Member02.builder()
                .userID(memberDto.getUserID())
                .nickName(memberDto.getNickName())
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
        /*return memberRepository.findAll()
                .stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());*/
        //반복문 돌려서 dto memberList에 담기...
        //return memberList;
    }

    public MemberDto getMemberInfo(String id) {
        Optional<Member02> memberEntity = memberRepository.findById(id);
        if(memberEntity.isPresent()) {
            MemberDto memberInfo = MemberDto.fromEntity(memberEntity.get());
            return memberInfo;
        }
        return null;
        //throw new NotFoundMember("해당 회원을 찾을 수 없습니다.");
    }

    public MemberDto modifyMember(MemberDto memberDto) {
        Optional<Member02> memberEntity = memberRepository.findById(memberDto.getUserID());
        if(memberEntity.isPresent()) {
            Member02 updateMember = Member02.builder()
                    .userID(memberDto.getUserID())
                    .nickName(memberDto.getNickName())
                    .age(memberDto.getAge())
                    .email(memberDto.getEmail())
                    .build();
            memberRepository.save(updateMember);
        }
        return null;
    }

    public boolean deleteMember(String id) {
        Optional<Member02> memberEntity = memberRepository.findById(id);
        if(memberEntity.isPresent()) {
            memberRepository.delete(memberEntity.get());
            return true;
        }
        return false;
    }
}

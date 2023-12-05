package com.ryan9025.jpa.service;

import com.ryan9025.jpa.dto.MemberDto;
import com.ryan9025.jpa.entity.Member02;
import com.ryan9025.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberDto joinMember(MemberDto memberDto) {
        Member02 dbJoinMember = Member02.builder()
                .userID(memberDto.getUserID())
                .password(bCryptPasswordEncoder.encode(memberDto.getPassword()))
                .nickName(memberDto.getNickName())
                .age(memberDto.getAge())
                .email(memberDto.getEmail())
                .role("ROLE_USER")
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

    public MemberDto getMemberInfo(String userID) {
        Optional<Member02> memberEntity = memberRepository.findByUserID(userID);
        if(memberEntity.isPresent()) {
            MemberDto memberInfo = MemberDto.fromEntity(memberEntity.get());
            log.info("memberInfo === " + memberInfo.toString());
            return memberInfo;
        }
        return null;
        //throw new NotFoundMember("해당 회원을 찾을 수 없습니다.");
    }
    @Transactional
    public MemberDto modifyMember(MemberDto memberDto) {
        log.info("getUserID==="+memberDto.getUserID());
        Optional<Member02> memberEntity = memberRepository.findByUserID(memberDto.getUserID());
        if(memberEntity.isPresent()) {
            Member02 updateMember = Member02.builder()
                    .nickName(memberDto.getNickName())
                    .age(memberDto.getAge())
                    .email(memberDto.getEmail())
                    .build();
            log.info("updateMember ==== " + updateMember.toString());
            memberRepository.save(updateMember);
        }
        return null;
    }

    public boolean deleteMember(String userID) {
        Optional<Member02> memberEntity = memberRepository.findByUserID(userID);
        if(memberEntity.isPresent()) {
            memberRepository.delete(memberEntity.get());
            return true;
        }
        return false;
    }
}

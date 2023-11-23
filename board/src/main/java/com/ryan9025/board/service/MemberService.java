package com.ryan9025.board.service;

import com.ryan9025.board.dao.MemberDao;
import com.ryan9025.board.dto.MemberDto;
import com.ryan9025.board.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberDao memberDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public int insertMember(@ModelAttribute MemberDto memberDto) {
        MemberDto insertMemberDto = MemberDto.builder()
                .id(memberDto.getId())
                .password(bCryptPasswordEncoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .build();
        int result = memberDao.insertMember(insertMemberDto);
        return result;
    }
    @Transactional
    public int deleteMember(@ModelAttribute LoginDto loginDto) {
        int result = 0;
        MemberDto dbLoginDto = memberDao.loginMember(loginDto.getId());
        //여기서 회원정보 가져오기!!
        if(bCryptPasswordEncoder.matches(loginDto.getPassword(),dbLoginDto.getPassword())) {
            String state = null;
            result = memberDao.deleteMember(loginDto);
            if(state == null) {
                throw new RuntimeException("오류발생");
            }
            memberDao.insertQuitMember(dbLoginDto);
        }
        return result;
    }

    public int updateMember(@ModelAttribute MemberDto memberDto) {
        int result = 0;
        MemberDto dbLoginDto = memberDao.loginMember(memberDto.getId());
        //여기서 회원정보 가져오기!!
        if(bCryptPasswordEncoder.matches(memberDto.getPassword(),dbLoginDto.getPassword())) {
            result = memberDao.updateMember(memberDto);
        }
        return result;
    }

    public int updatePassword(@ModelAttribute LoginDto loginDto) {
        int result = 0;
        return result;
    }
}

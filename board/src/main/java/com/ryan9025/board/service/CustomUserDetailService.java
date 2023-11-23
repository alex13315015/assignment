package com.ryan9025.board.service;

import com.ryan9025.board.dao.MemberDao;
import com.ryan9025.board.dto.CustomUserDetails;
import com.ryan9025.board.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberDao memberDao;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        MemberDto loggedMember = memberDao.loginMember(id);
        if(loggedMember != null) {
            return new CustomUserDetails(loggedMember);
        }
       throw new UsernameNotFoundException("아이디 패스워드를 확인해주세요.");
    }
}

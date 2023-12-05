package com.ryan9025.jpa.service;

import com.ryan9025.jpa.entity.Member02;
import com.ryan9025.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
        Optional<Member02> loggedMember = memberRepository.findByUserID(userID);
        if(loggedMember.isPresent()) {

        }
        return null;
    }
}

package com.ryan9025.myhomepage.service;

import com.ryan9025.myhomepage.constant.Role;
import com.ryan9025.myhomepage.dto.JoinDto;
import com.ryan9025.myhomepage.dto.UpdateMemberDto;
import com.ryan9025.myhomepage.entity.Member;
import com.ryan9025.myhomepage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${file.path}")
    private String uploadFolder;
    @Transactional
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
    @Transactional
    public void updateMember(int id, UpdateMemberDto updateMemberDto) {
        Optional<Member> findMember = memberRepository.findById(id);
        if(findMember.isPresent()) {
            Member member = findMember.get();
            member.setName(updateMemberDto.getName());
            member.setEmail(updateMemberDto.getEmail());
            member.setPhoneNumber(updateMemberDto.getPhoneNumber());
            member.setMbti(updateMemberDto.getMbti());
            member.setDescription(updateMemberDto.getDescription());
        } else {
            throw new UsernameNotFoundException("등록되지 않은 회원입니다.");
        }
    }
    @Transactional
    public void changeProfile(int id, MultipartFile profileImageUrl) {
        log.info("id==={}",id);
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + profileImageUrl.getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
        try {
            Files.write(imageFilePath,profileImageUrl.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isPresent()) {
            optionalMember.get().setProfileImageUrl(imageFileName);
        } else {
            throw new UsernameNotFoundException("등록되지 않은 회원입니다.");
        }
    }
}

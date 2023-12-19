package com.ryan9025.myhomepage.service;

import com.ryan9025.myhomepage.constant.Role;
import com.ryan9025.myhomepage.dto.JoinDto;
import com.ryan9025.myhomepage.dto.MemberProfileDto;
import com.ryan9025.myhomepage.dto.UpdateMemberDto;
import com.ryan9025.myhomepage.entity.Member;
import com.ryan9025.myhomepage.repository.MemberRepository;
import com.ryan9025.myhomepage.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    private final SubscribeRepository subscribeRepository;

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
                .phoneNumber(joinDto.getPhoneNumber())
                .description(joinDto.getDescription())
                .mbti(joinDto.getMbti())
                .build();
        return memberRepository.save(dbJoinMember);
    }
    @Transactional
    public Member updateMember(int id, UpdateMemberDto updateMemberDto) {
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
        return null;
    }
    @Transactional
    public Member changeProfile(int id, MultipartFile profileImageUrl) {
        log.info("id==={}",id);
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + profileImageUrl.getOriginalFilename();
        String thumbnailFileName = "thumb_" + imageFileName;
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
        File originalFile = new File(uploadFolder + imageFileName);
        try {
            Files.write(imageFilePath,profileImageUrl.getBytes());
            Thumbnailator.createThumbnail(originalFile,
                                          new File(uploadFolder + thumbnailFileName), 150,150);
            originalFile.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Optional<Member> optionalMember = memberRepository.findById(id); // 엔티티 member 찾아서
        if(optionalMember.isPresent()) {
            optionalMember.get().setProfileImageUrl(thumbnailFileName); // setter로 update
            return optionalMember.get();
        } else {
            throw new UsernameNotFoundException("등록되지 않은 회원입니다.");
        }

    }

    @Transactional
    public MemberProfileDto getProfile(int id, int customerDetailsID) {
        MemberProfileDto memberProfileDto = new MemberProfileDto();
        Member memberInfo =
                memberRepository.findById(id).orElseThrow(
                        () -> new UsernameNotFoundException("등록되지 않은 회원입니다.")
                );
        int subscribeCount = subscribeRepository.subscribeCount(id);
        int subscribeState = subscribeRepository.subscribeState(customerDetailsID,id);
        memberProfileDto.setPageOwner(id == customerDetailsID);
        memberProfileDto.setMember(memberInfo);
        memberProfileDto.setSubscribeCount(subscribeCount);
        memberProfileDto.setSubscribeState(subscribeState >= 1);
        memberProfileDto.setLikeTotal(memberInfo.getImageList().size());

        memberInfo.getImageList().forEach((image)->{
            log.info("image.getLikes().size()==={}",image.getLikes().size());
            image.setLikeTotal(image.getLikes().size());
        });
        //memberProfileDto.setLikeTotal();
        return memberProfileDto;
    }

}


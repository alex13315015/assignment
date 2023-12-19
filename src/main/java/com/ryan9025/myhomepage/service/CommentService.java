package com.ryan9025.myhomepage.service;

import com.ryan9025.myhomepage.entity.Comments;
import com.ryan9025.myhomepage.entity.Image;
import com.ryan9025.myhomepage.entity.Member;
import com.ryan9025.myhomepage.repository.CommentRepository;
import com.ryan9025.myhomepage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public Comments saveComment(String content, int imageID, int customDetailsID) {
        Image image = Image.builder()
                .id(imageID)
                .build();
        Member memberEntity = memberRepository.findById(customDetailsID).orElseThrow(() -> {
            throw new UsernameNotFoundException("등록되지 않은 회원입니다.");
        });
        /*Optional<Member> optionalMember = memberRepository.findById(customDetailsID);
        if(!optionalMember.isEmpty()) {
            memberEntity = optionalMember.get();
        }*/
        Comments comments = Comments.builder()
                .content(content)
                .image(image)
                .member(memberEntity)
                .build();
        return commentRepository.save(comments);
    }
    @Transactional
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}

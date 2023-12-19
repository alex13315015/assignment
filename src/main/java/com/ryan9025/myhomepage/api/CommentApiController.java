package com.ryan9025.myhomepage.api;

import com.ryan9025.myhomepage.dto.CommentDto;
import com.ryan9025.myhomepage.dto.CustomUserDetails;
import com.ryan9025.myhomepage.entity.Comments;
import com.ryan9025.myhomepage.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {
    private final CommentService commentService;
    @PostMapping("/comment")
    public Map<String,Object> saveComment(CommentDto commentDto,
                                          @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Comments comments = commentService.saveComment(
                commentDto.getContent(),
                commentDto.getImageID(),
                customUserDetails.getLoggedMember().getId()
                );
        Map<String,Object> resultMap = new HashMap<>();
        log.info("commentDto==={}",commentDto);
        resultMap.put("insertComment","OK");
        resultMap.put("comments",comments);
        return resultMap;
    }

    @DeleteMapping("/comment/{id}")
    public Map<String,Object> deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("deleteComment","OK");
        return resultMap;
    }
}

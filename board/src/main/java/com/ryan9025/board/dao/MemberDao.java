package com.ryan9025.board.dao;

import com.ryan9025.board.dto.MemberDto;
import com.ryan9025.board.dto.LoginDto;
import com.ryan9025.board.dto.UpdateDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    int insertMember(MemberDto memberDto);

    MemberDto loginMember(String username);

    int deleteMember(LoginDto loginDto);

    int updateMember(MemberDto memberDto);

    int insertQuitMember(MemberDto memberDto);

    int updatePassword(UpdateDto updateDto);
}

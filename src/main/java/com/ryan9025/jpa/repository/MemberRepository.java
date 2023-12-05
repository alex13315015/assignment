package com.ryan9025.jpa.repository;

import com.ryan9025.jpa.entity.Member02;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member02,String> {
    List<Member02> findByNickName(String NickName);
    List<Member02> findByNickNameOrUserID(String NickName,String UserID);
    List<Member02> findByAgeGreaterThanOrderByAgeDesc(int age);
    Optional<Member02> findByUserID(String UserID);
}

package com.ryan9025.myhomepage.repository;

import com.ryan9025.myhomepage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    Optional<Member> findByUserID(String userID);
}

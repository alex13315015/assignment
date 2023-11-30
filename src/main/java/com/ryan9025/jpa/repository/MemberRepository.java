package com.ryan9025.jpa.repository;

import com.ryan9025.jpa.entity.Member02;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member02,Integer> {
}

package com.ryan9025.jpa.repository;

import com.ryan9025.jpa.entity.Board02;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board02,Integer> {
    //기본적인 crud 기능 다 있음
    // insert == save
    // select == find
    // update == save
    // delete == delete
    @Override
    Page<Board02> findAll(Pageable pageable);

    //jpql
    /*@Modifying
    @Query("select b from Board02 b where b.subject like %:keyword%")
    List<Board02> findBySubject(@Param("keyword") String keyword, Pageable pageable);*/

    //sql
    @Query(value = "select b from Board02 b where b.subject like %:keyword%")
    Page<Board02> findBySubject(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select b from Board02 b where b.content like %:keyword%")
    Page<Board02> findByContent(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select b from Board02 b where b.writer.nickName like %:keyword%")
    Page<Board02> findByWriter(@Param("keyword") String keyword, Pageable pageable);

}

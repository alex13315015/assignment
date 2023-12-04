package com.ryan9025.jpa.repository;

import com.ryan9025.jpa.entity.Board02;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board02,Integer> {
    //기본적인 crud 기능 다 있음
    // insert == save
    // select == find
    // update == save
    // delete == delete


    @Override
    Page<Board02> findAll(Pageable pageable);

}

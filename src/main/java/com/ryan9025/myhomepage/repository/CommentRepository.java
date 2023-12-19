package com.ryan9025.myhomepage.repository;

import com.ryan9025.myhomepage.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments,Integer> {
    //crud
    //@Query를 통한 crud --> returnType : int
    // JpaRepository(save,delete)를 통한 crud --> returnType : Entity
}

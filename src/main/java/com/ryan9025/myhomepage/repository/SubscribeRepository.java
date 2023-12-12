package com.ryan9025.myhomepage.repository;

import com.ryan9025.myhomepage.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe,Integer> {
    @Modifying
    @Query(value = "INSERT INTO SUBSCRIBE" +
            "(id,fromMemberID,toMemberID,createDate) VALUES (subscribe_seq.nextval,:fromMemberID,:toMemberID,sysdate)"
            ,nativeQuery = true)
    void subscribe(@Param("fromMemberID") int fromMemberID, @Param("toMemberID") int toMemberID);



    @Modifying
    @Query(value = "SELECT COUNT(*) FROM SUBSCRIBE WHERE fromMemberID = :memberID",nativeQuery = true)
    int subscribeCount(@Param("memberID") int memberID);
}

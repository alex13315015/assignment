package com.ryan9025.myhomepage.repository;

import com.ryan9025.myhomepage.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {
    @Query(value = "SELECT * FROM IMAGE i WHERE MEMBER_ID IN" +
            " (SELECT TOMEMBERID FROM SUBSCRIBE WHERE FROMMEMBERID = :customerDetailsID)"
            , nativeQuery = true)
    Page<Image> loadFeed(@Param("customerDetailsID") int customerDetailsID, Pageable pageable);

    @Query(value = "SELECT * FROM IMAGE i " +
            "INNER JOIN (SELECT imageID, count(imageID) AS likeCount " +
            "FROM LIKES l " +
            "GROUP BY IMAGEID) c " +
            "ON i.id = c.imageID " +
            "ORDER BY LIKECOUNT DESC", nativeQuery = true)
    Page<Image> popular(Pageable pageable);
}

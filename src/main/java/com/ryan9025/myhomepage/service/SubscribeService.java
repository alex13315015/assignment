package com.ryan9025.myhomepage.service;

import com.ryan9025.myhomepage.dto.SubscribeDto;
import com.ryan9025.myhomepage.repository.SubscribeRepository;
import groovy.util.logging.Slf4j;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final EntityManager entityManager;
    @Transactional
    public void subscribe(int customerDetailsID, int urlID) {
        subscribeRepository.subscribe(customerDetailsID, urlID);
    }
    @Transactional
    public void unSubscribe(int customerDetailsID, int urlID) {
        subscribeRepository.unSubscribe(customerDetailsID,urlID);
    }
    @Transactional
    public int subscribeCount(int memberID) {
        return subscribeRepository.subscribeCount(memberID);
    }

    //구독 리스트 만들기
    @Transactional
    public List<SubscribeDto> subscribeList(int customUserDetailsID, int urlID) {
        String queryString =
                "SELECT b.MEMBER_ID AS ID , b.PROFILEIMAGEURL , b.NAME, " +
                "NVL2((SELECT 1 FROM SUBSCRIBE WHERE FROMMEMBERID = ? AND TOMEMBERID = b.MEMBER_ID),TO_CHAR(1),TO_CHAR(0)) " +
                "AS subscribeState, " +
                "NVL2((SELECT 1 FROM dual WHERE ? = b.MEMBER_ID),TO_CHAR(1),TO_CHAR(0)) AS equalState " +
                "FROM BLOG_MEMBER b INNER JOIN SUBSCRIBE s " +
                "ON b.MEMBER_ID = s.TOMEMBERID " +
                "WHERE s.FROMMEMBERID = ?";
        Query query = entityManager.createNativeQuery(queryString)
                .setParameter(1,customUserDetailsID)
                .setParameter(2,customUserDetailsID)
                .setParameter(3,urlID);
        //QLRM
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<SubscribeDto> subscribeList = jpaResultMapper.list(query,SubscribeDto.class);
        return subscribeList;
    }
}

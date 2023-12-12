package com.ryan9025.myhomepage.service;

import com.ryan9025.myhomepage.repository.SubscribeRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
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



}

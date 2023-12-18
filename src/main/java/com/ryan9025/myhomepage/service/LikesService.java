package com.ryan9025.myhomepage.service;

import com.ryan9025.myhomepage.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikesService {
    private final LikesRepository likesRepository;

    @Transactional
    public int like(int imageID,int customerDetailsID) {
        int result = likesRepository.like(imageID,customerDetailsID);
        return result;
    }
}

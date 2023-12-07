package com.ryan9025.jpa.repository;

import com.ryan9025.jpa.entity.Board02;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard02() {
        for(int i =1; i <= 120; i++) {
            Board02 dbInsertBoard = Board02.builder()
                    .id(i)
                    .subject("제목이 들어갑니다." + i)
                    .content("내용이 들어갑니다." + i)
                    .createDate(LocalDateTime.now())
                    .build();
            Board02 responseBoard = boardRepository.save(dbInsertBoard);
        }
    }
}

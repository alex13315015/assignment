package com.ryan9025.jpa.repository;

import com.ryan9025.jpa.entity.Member02;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("1+2는 3이다")
    public void test() {
        int a = 1;
        int b = 2;
        int sum = 3;
        //Assertions.assertEquals(sum, a + b);

    }


    public void joinMember() {
        for (int i = 0; i < 10; i++) {
            Member02 dbInsertMember = Member02.builder()
                    .userID("ryan9025" + i)
                    .email("alex" + i + "@naver.com")
                    .nickName("라이언" + i)
                    .age(10 + i)
                    .build();
            memberRepository.save(dbInsertMember);
        }
    }

    @Test
    @DisplayName("이름으로 조회")
    public void findByNickNameTest() {
        joinMember();
        List<Member02> memberList = memberRepository.findByNickName("라이언2");
        Assertions.assertThat(memberList.size()).isGreaterThan(0);
        //Assertions.assertEquals(1, memberList.size());
//        for(Member02 item: memberList) {
//            System.out.println(item.toString());
//        }

    }
}
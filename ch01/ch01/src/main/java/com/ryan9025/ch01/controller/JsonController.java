package com.ryan9025.ch01.controller;

import com.ryan9025.ch01.dto.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

    @GetMapping("/response/json")
    public Person responseJson() {
        //String 말고 객체를 던져주면 됨 --> MessageConverter 가 스프링 내부에서 동작
        Person person = Person
        .builder()
                .age(10)
                .name("장일성")
                .address("경기도 오산시")
                .tel("010-7925-0693")
                .build();


        return person;
    }

}

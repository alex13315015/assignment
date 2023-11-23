package com.ryan9025.json;

import com.ryan9025.json.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HttpBodyController {

    // get/post/put/delete
    @PostMapping("/body01")
    public String xwwwformulenceded(String name) {
        log.info("클라이언트에서 넘어온 name==={}",name);
        return  "httpBody 전송방식 중 하나인 key value 넘어옴";
    }

    @PostMapping("/body02")
    public String textplain(@RequestBody String data) {
        log.info("클라이언트에서 넘어온 data==={}",data);
        return  "textplain";
    }

    @PostMapping("/body03")
    public String applicationjson(@RequestBody String data) {
        log.info("클라이언트에서 넘어온 data==={}",data);
        return  "application/json";
    }

    @PostMapping("/body04")
    public String applicationjsonToObject(@RequestBody Member member) {
        log.info("클라이언트에서 넘어온 data==={}",member);
        log.info("member==={}",member);
        log.info("name==={}",member.getName());
        log.info("age==={}",member.getAge());
        return  "application/json";
    }
}

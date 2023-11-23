package com.ryan9025.board.service;

import com.ryan9025.board.dao.MemberDao;
import com.ryan9025.board.dto.LoginDto;
import com.ryan9025.board.dto.MailDto;
import com.ryan9025.board.dto.UpdateDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MemberDao memberdao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void sendMail(MailDto mailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailDto.getReceiver());
        simpleMailMessage.setFrom("alex7006@naver.com");
        simpleMailMessage.setSubject(mailDto.getTitle());
        simpleMailMessage.setText(mailDto.getContent());
        javaMailSender.send(simpleMailMessage);
    }

    private String randomNumber;

    public void createRandomNumber() {
        randomNumber = "" + ((int)(Math.random() *90000) + 10000);
        log.info("randomNumber==={}",randomNumber);
    }

    public MimeMessage createMail(String mail) {
        //랜덤숫자 생성
        createRandomNumber();
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.setFrom("alex7006@naver.com");
            message.setRecipients(MimeMessage.RecipientType.TO,mail);
            message.setSubject("이메일 검증");
            String content = "<h2>요청하신 인증번호입니다.</h2>";
            content+="<h1>" + randomNumber + "</h1>";
            message.setText(content,"UTF-8","html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }
    public String sendAuthEmail(String mail) {
        MimeMessage message = createMail(mail);
        //db에 비밀번호를 생성된 임시비밀번호를 암호화해서 저장!
        javaMailSender.send(message);
        return randomNumber;
    }
    @Transactional
    public void sendTempPassword(UpdateDto updateDto) {
        String randomNum = sendAuthEmail(updateDto.getEmail());
        UpdateDto dbUpdateDto = UpdateDto.builder()
                .email(updateDto.getEmail())
                .password(bCryptPasswordEncoder.encode(randomNum))
                .build();
        memberdao.updatePassword(updateDto);
    }
}

package com.ryan9025.board.handler;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class UserLoginFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = "";
        if(exception instanceof BadCredentialsException) {
            errorMsg = "아이디 패스워드를 확인해주세요.";
        }else {
            errorMsg = "알 수 없는 오류입니다. 관리자에게 문의하세요.";
        }
        errorMsg = URLEncoder.encode(errorMsg, "UTF-8");
        setDefaultFailureUrl("/member/login?error=true&exception=" + errorMsg);
        super.onAuthenticationFailure(request,response,exception);
    }

}

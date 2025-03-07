package ilike.ildrio.config.security;

import ilike.ildrio.common.SessionUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class LoginSuccessHandler implements AuthenticationSuccessHandler {


    /**
     * 스프링 시큐리티 로그인 성공 핸들러
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        
    }

}

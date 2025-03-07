package ilike.ildrio.config.security;

import ilike.ildrio.common.SessionUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.inject.Inject;
import javax.servlet.http.Cookie;

import java.io.IOException;


public class LogoutSuccessHandler implements LogoutHandler {

    private final SessionUtil sessUtil;
 
    public LogoutSuccessHandler(SessionUtil session) {
        this.sessUtil = session;
     }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

       //
    }


}

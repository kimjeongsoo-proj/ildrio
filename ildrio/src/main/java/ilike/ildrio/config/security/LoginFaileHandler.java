package ilike.ildrio.config.security;

import ilike.ildrio.common.SessionUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class LoginFaileHandler implements AuthenticationFailureHandler {
    private final SessionUtil sessUtil;

    public LoginFaileHandler (SessionUtil sessUtil) {
    	this.sessUtil = sessUtil;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
    	
         response.sendRedirect("/member/login?error");
    }

}

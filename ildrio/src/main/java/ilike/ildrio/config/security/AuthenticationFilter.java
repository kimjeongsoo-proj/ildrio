package ilike.ildrio.config.security;


import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String SPRING_SECURITY_FORM_DOMAIN_KEY = "domain";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        AuthenticationToken authRequest = getAuthRequest(request);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private AuthenticationToken getAuthRequest(HttpServletRequest request) {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String domain = obtainDomain(request);

        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }
        if (domain == null) {
            domain = "USR";
        }

        return new AuthenticationToken(username, password, domain);
    }

    private String obtainDomain(HttpServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_DOMAIN_KEY);
    }
}

package ilike.ildrio.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationToken extends UsernamePasswordAuthenticationToken {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String domain;

    public AuthenticationToken(Object principal, Object credentials, String domain) {
        super(principal, credentials);
        this.domain = domain;
    }

    public AuthenticationToken(Object principal, Object credentials, String domain,
                                     Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.domain = domain;
    }

    public String getDomain() {
        return this.domain;
    }
}

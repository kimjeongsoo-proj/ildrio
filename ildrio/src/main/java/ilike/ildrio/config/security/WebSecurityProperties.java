package ilike.ildrio.config.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "ilike.security")
public class WebSecurityProperties {


    /**
     * 로그인 페이지 URL
     */
    private String loginUrl = "/member/login";
    private String loginProcessingUrl = "/member/login-process";

    private String successForwardUrl = "/index";
    private String failureForwardUrl = "/member/login?error";

    private String usernameParameter = "userid";
    private String passwordParameter = "password";

    private String logoutUrl = "/member/logout";

}

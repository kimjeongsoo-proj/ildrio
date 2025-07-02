package ilike.ildrio.config;

import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SameSiteCookieConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        System.out.println("✅ SameSiteCookieConfig 실행됨!!"); // ← 이거 콘솔에 찍혀야 합니다

        factory.addContextCustomizers(context -> {
            Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
            cookieProcessor.setSameSiteCookies("None");
            context.setCookieProcessor(cookieProcessor);
        });
    }
}

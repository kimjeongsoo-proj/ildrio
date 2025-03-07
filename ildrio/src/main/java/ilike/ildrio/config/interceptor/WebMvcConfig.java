package ilike.ildrio.config.interceptor;

import ilike.ildrio.common.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


	@Autowired
	private SessionUtil sessionUtil;
  

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new WebInterceptor(sessionUtil))
                .addPathPatterns("/**")
                 .excludePathPatterns("/not-found/**")
                .excludePathPatterns("/not-access/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/error")
                .excludePathPatterns("/member/**")
                .excludePathPatterns("/uploads/**")
                .excludePathPatterns("/resources/**"); // 해당 경로는 인터셉터가 가로채지 않는다.
      }
    
   

   
}

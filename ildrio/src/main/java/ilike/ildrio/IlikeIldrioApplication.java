package ilike.ildrio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(basePackages = "ilike.ildrio.mapper")
public class IlikeIldrioApplication extends SpringBootServletInitializer {

	// 외부 톰캣 서버 사용을 위한 설정
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(IlikeIldrioApplication.class);
	} 
	
	//기본
	public static void main(String[] args) {
		SpringApplication.run(IlikeIldrioApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer adminCorsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOriginPatterns();
			}
		};
	}
	
}

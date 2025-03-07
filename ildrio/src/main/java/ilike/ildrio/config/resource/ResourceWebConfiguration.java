package ilike.ildrio.config.resource;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer {
    @Value("${image.base-folder:images}")
    private String baseFolder;
    
    private String connectPath = "/uploads/**";
    private String resourcePath = "file:///C:/LOCAL_FILE/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);
    }

}

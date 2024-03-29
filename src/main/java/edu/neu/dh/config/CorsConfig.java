package edu.neu.dh.config;

import edu.neu.dh.utils.CrossInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
    public void addInterceptors(InterceptorRegistry registry) {
        CrossInterceptor crossInterceptor = new CrossInterceptor();
        registry.addInterceptor(crossInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}

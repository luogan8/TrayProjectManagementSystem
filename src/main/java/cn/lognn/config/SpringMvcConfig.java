package cn.lognn.config;

import cn.lognn.controller.interceptor.ProjectInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/admin/index.html");
        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/trays/addTrays");
        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/traysLR");
        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/traysNG");
        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/traysOutside");
        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/trayMenu");
        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/traysYard");
        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/download/ng/**");
//        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/download/lr/**");
        registry.addInterceptor(new ProjectInterceptor()).addPathPatterns("/download/info");
    }

}

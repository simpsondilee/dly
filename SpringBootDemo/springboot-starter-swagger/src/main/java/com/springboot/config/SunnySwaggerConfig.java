package com.springboot.config;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author:DuLeYan
 * @Description
 * @Date Created in 15:01 2019/7/11
 */
@Configuration
@EnableSwagger2Doc
@PropertySource(value = "classpath:springboot-start-core/application-dev.properties")
public class SunnySwaggerConfig extends WebMvcConfigurerAdapter {
    /**
     * 注入swagger资源文件
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}

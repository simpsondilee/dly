package com.springboot.core.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author:DuLeYan
 * @Description
 * @Date Created in 15:52 2019/6/28
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@PropertySource(value = "classpath:application-dev.properties")
public class SpringBootCoreConfig {
}

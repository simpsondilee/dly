package com.springboot.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * @Author:DuLeYan
 * @Description
 * @Date Created in 14:58 2019/6/28
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 注意这里的扫描路径: 1.不要扫描到自定义的Mapper; 2.定义的路径不要扫描到tk.mybatis.mapper(如定义**.mapper).
        // 两个做法都会导致扫描到tk.mybatis的Mapper，就会产生重复定义的报错.
        mapperScannerConfigurer.setBasePackage("**.springboot.**.mapper");
        return mapperScannerConfigurer;
    }
}

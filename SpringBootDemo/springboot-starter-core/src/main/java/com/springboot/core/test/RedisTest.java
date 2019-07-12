package com.springboot.core.test;

import com.springboot.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @Author:DuLeYan
 * @Description
 * @Date Created in 9:37 2019/7/11
 */
public class RedisTest {

    @Autowired
    RedisUtil redisUtil;

    @PostConstruct
    public void testRedis(String key) {
        redisUtil.set("ddddd", "MyValue");
        String result = (String) redisUtil.get("ddddd");
        System.out.print("-------------测试redis工具类：" + result);
    }

    public static void main(String[] args) {
//        RedisTest t = new RedisTest();
//        t.testRedis("dly");
    }

}

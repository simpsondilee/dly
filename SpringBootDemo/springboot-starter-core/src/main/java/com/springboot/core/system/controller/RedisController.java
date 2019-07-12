package com.springboot.core.system.controller;

import com.springboot.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:DuLeYan
 * @Description
 * @Date Created in 9:50 2019/7/11
 */

@RestController
public class RedisController {

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "/sys/testredis")
    public void testRedis(){
        redisUtil.set("dly","dly");
    }

}

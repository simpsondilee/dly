package com.springboot.core.system.controller;

import com.springboot.core.base.Result;
import com.springboot.core.constants.BaseEnums;
import com.springboot.core.constants.Constants;
import com.springboot.core.system.dto.User;
import com.springboot.core.system.service.UserService;
import com.springboot.core.util.Dates;
import com.springboot.core.util.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(tags="用户管理")
@RequestMapping
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static List<User> userList = new ArrayList<>();

//    // 先静态模拟数据
//    static {
//        User user1 = new User();
//        user1.setUserId(1L);
//        user1.setUsername("lufei");
//        user1.setNickname("蒙奇D路飞");
//        user1.setBirthday(Dates.parseDate("2000-05-05"));
//        user1.setSex(Constants.Sex.MALE);
//        user1.setEnabled(Constants.Flag.YES);
//        userList.add(user1);
//
//        User user2 = new User();
//        user2.setUserId(2L);
//        user2.setUsername("nami");
//        user2.setNickname("娜美");
//        user2.setBirthday(Dates.parseDate("2000/7/3"));
//        user2.setSex(Constants.Sex.FEMALE);
//        user2.setEnabled(Constants.Flag.YES);
//        userList.add(user2);
//    }

    @ApiOperation("查找所有用户")
    @ApiImplicitParam(name = "", value = "", paramType = "path")
    @RequestMapping("/sys/user/queryAll")
    public Result queryAll(){
        List<User> list = userService.selectAll();
        return Results.successWithData(list, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    @ApiOperation("查找单个用户")
    @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "path")
    @RequestMapping("/sys/user/queryOne/{userId}")
    public Result queryOne(@PathVariable Long userId){
        User user = userService.get(userId);
        logger.debug("userId:{},username:{},birthday{}",user.getUserId(),user.getUsername(),user.getBirthday());
        logger.info("userId:{},username:{},birthday{}",user.getUserId(),user.getUsername(),user.getBirthday());
        logger.error("userId:{},username:{},birthday{}",user.getUserId(),user.getUsername(),user.getBirthday());
        return Results.successWithData(user);
    }

    @ApiOperation("保存用户")
    @PostMapping("/sys/user/save")
    public Result save(@ApiParam(name = "user", value = "用户")@RequestBody User user){
        user = userService.insertSelective(user);
        return Results.successWithData(user);
    }

    @PostMapping("/sys/user/update")
    public Result update(@Valid @RequestBody List<User> user){
        user = userService.persistSelective(user);
        return Results.successWithData(user);
    }

    @RequestMapping("/sys/user/delete")
    public Result delete(User user){
        userService.delete(user);
        return Results.success("success");
    }

    @RequestMapping("/sys/user/delete/{userId}")
    public Result delete(@PathVariable Long userId){
        userService.delete(userId);
        return Results.success("success");
    }
}

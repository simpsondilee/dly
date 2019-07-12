package com.springboot.core.system.service.impl;

import com.springboot.core.base.BaseService;
import com.springboot.core.system.dto.User;
import com.springboot.core.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author:DuLeYan
 * @Description
 * @Date Created in 16:24 2019/6/28
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {
    @Override
    public int delete(String ids) {
        return 0;
    }
}

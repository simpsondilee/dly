package com.springboot.core.base.exception;

/**
 * @Author:DuLeYan
 * @Description
 * @Date Created in 16:42 2019/7/1
 */
public class AuthorityException extends BaseException{

    private static final long serialVersionUID = 4014682918350743441L;

    public AuthorityException() {}

    public AuthorityException(String message) {
        super(message);
    }

    public AuthorityException(String code, String message) {
        super(code, message);
    }
}

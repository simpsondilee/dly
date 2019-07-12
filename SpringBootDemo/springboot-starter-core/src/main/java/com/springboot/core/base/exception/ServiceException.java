package com.springboot.core.base.exception;

public class ServiceException extends BaseException{

    private static final long serialVersionUID = -5137659671644986308L;

    public ServiceException() {}

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(code, message);
    }

}

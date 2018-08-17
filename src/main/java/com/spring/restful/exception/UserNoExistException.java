package com.spring.restful.exception;

public class UserNoExistException extends RuntimeException {

    public UserNoExistException() {
        super("用户信息不存在!");
    }
}

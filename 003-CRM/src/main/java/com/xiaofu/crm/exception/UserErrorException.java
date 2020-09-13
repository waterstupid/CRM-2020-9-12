package com.xiaofu.crm.exception;

public class UserErrorException extends Exception {
    // 重写这个方法
    public UserErrorException(String message) {
        super(message);
    }
}

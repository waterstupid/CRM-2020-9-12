package com.xiaofu.crm.settings.service;

import com.xiaofu.crm.settings.domain.User;
import com.xiaofu.crm.settings.exception.UserErrorException;

import java.util.Map;

public interface UserService {

    User login(String username, String password, String ip) throws UserErrorException;
}

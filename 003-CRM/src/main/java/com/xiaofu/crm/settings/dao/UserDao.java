package com.xiaofu.crm.settings.dao;

import com.xiaofu.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    User login(Map<String,String> map);

    List<User> selectAll();
}

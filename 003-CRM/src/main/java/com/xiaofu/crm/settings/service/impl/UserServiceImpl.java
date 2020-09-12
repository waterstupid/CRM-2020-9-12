package com.xiaofu.crm.settings.service.impl;

import com.xiaofu.crm.settings.dao.UserDao;
import com.xiaofu.crm.settings.domain.User;
import com.xiaofu.crm.settings.exception.UserErrorException;
import com.xiaofu.crm.settings.service.UserService;
import org.apache.ibatis.session.SqlSession;
import util.DateTimeUtil;
import util.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao= SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String username, String password, String ip) throws UserErrorException {
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        User user = userDao.login(map);
        if(user == null){
            // user查出来为null,则抛出一个用户密码错误的异常
            throw new UserErrorException("用户密码错误");
        }else{
            // 如果user不为null,则还需要判断expireTime是否过期
            // 首先是要获取当前系统时间
            String time= DateTimeUtil.getSysTime();
            // 然后与当前系统时间比较
            if(user.getExpireTime().compareTo(time) < 0){
                throw new UserErrorException("用户已过期");
            // 判断是否锁定了
            }else if("0".equals(user.getLockState())){
                throw new UserErrorException("用户已锁定");
            }else if(!user.getAllowIps().contains(ip)){
                throw new UserErrorException("用户ip地址不在其中");
            }else{
                return user;
            }
        }

    }
}

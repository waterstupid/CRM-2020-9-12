package com.xiaofu.crm.settings.web.controller;

import com.xiaofu.crm.settings.domain.User;
import com.xiaofu.crm.settings.service.UserService;
import com.xiaofu.crm.settings.service.impl.UserServiceImpl;
import util.PrintJson;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if("/user/login.do".equals(path)){
            System.out.println("进入到登录页面");
            login(req,resp);
        }
    }
    // 首先明白控制器是要接收参数然后将任务交给service层处理
    private void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // 获取姓名
        String username = request.getParameter("username");
        System.out.println(username);
        // 获取密码
        String password = request.getParameter("password");
        // 获取id地址
        String ip = request.getRemoteAddr();
        // 然后调用service中的方法
        // 用动态代理的方式来获取代理对象，然后加上事务的功能即可
        UserService userService=new UserServiceImpl();
        userService= (UserService) ServiceFactory.getService(userService);
        try{
            User user = userService.login(username, password, ip);
            // 如果user不为null,则说明查询到了数据
            // 则需要向前端传送json数据:{"success":true}
            PrintJson.printJsonFlag(response,true);
            // 向session域中添加数据
            request.getSession().setAttribute("user",user);
        }catch(Exception e){
            e.printStackTrace();
            Map<String,Object> map=new HashMap<String,Object>();
            // 如果抛出异常,则说明用户名和密码是错误的
            // 则需要向前端传送json数据:{"success":true,"msg":错误信息}
            // 那么这个时候可以使用两种方法，一种是使用对象封装，一种是使用Map来封装
            // 两种方法的选取取决于使用的使用的频率
            map.put("success",false);
            map.put("msg",e.getMessage());
            // 将map对象变成json数据,并返回给浏览器
            PrintJson.printJsonObj(response,map);

        }




    }
}

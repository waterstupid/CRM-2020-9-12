package com.xiaofu.crm.workbench.web.controller;

import com.xiaofu.crm.settings.domain.User;
import com.xiaofu.crm.settings.service.UserService;
import com.xiaofu.crm.settings.service.impl.UserServiceImpl;
import com.xiaofu.crm.util.DateTimeUtil;
import com.xiaofu.crm.util.PrintJson;
import com.xiaofu.crm.util.ServiceFactory;
import com.xiaofu.crm.util.UUIDUtil;
import com.xiaofu.crm.workbench.domain.Activity;
import com.xiaofu.crm.workbench.service.ActivityService;
import com.xiaofu.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ActivityServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if("/activity/getUser.do".equals(path)){
            System.out.println("查询方法开始执行");
            get(response);
        }else if("/activity/save.do".equals(path)){
            System.out.println("save保存方法开始");
            save(response,request);
        }


    }

    private void save(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String id = UUIDUtil.getUUID();
        String owner=user.getId();
        String createBy=user.getName();
        String createTime= DateTimeUtil.getSysTime();
        Activity activity=new Activity();
        activity.setName(name);
        activity.setCost(cost);
        activity.setCreateBy(createBy);
        activity.setDescription(description);
        activity.setEndDate(endDate);
        activity.setId(id);
        activity.setOwner(owner);
        activity.setCreateTime(createTime);
        activity.setStartDate(startDate);
        ActivityService activityService=new ActivityServiceImpl();
        // 获得代理对象
        activityService= (ActivityService) ServiceFactory.getService(activityService);
        boolean flag=activityService.save(activity);
        if(flag){
            PrintJson.printJsonFlag(response,flag);
        }else{
            PrintJson.printJsonFlag(response,flag);
        }


    }

    private void get(HttpServletResponse response) {
        UserService userService=new UserServiceImpl();
        List<User> list=userService.getUserList();
        // 然后将这个List集合转换为json对象,然后输出到前端
        PrintJson.printJsonObj(response,list);
    }
}

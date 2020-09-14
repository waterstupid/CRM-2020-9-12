package com.xiaofu.crm.workbench.web.controller;

import com.xiaofu.crm.settings.domain.User;
import com.xiaofu.crm.settings.service.UserService;
import com.xiaofu.crm.settings.service.impl.UserServiceImpl;
import com.xiaofu.crm.util.DateTimeUtil;
import com.xiaofu.crm.util.PrintJson;
import com.xiaofu.crm.util.ServiceFactory;
import com.xiaofu.crm.util.UUIDUtil;
import com.xiaofu.crm.vo.Paging;
import com.xiaofu.crm.workbench.domain.Activity;
import com.xiaofu.crm.workbench.service.ActivityService;
import com.xiaofu.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println("service方法执行");
        if("/workbench/activity/getUser.do".equals(path)){
            System.out.println("查询方法开始执行");
            get(response);
        }else if("/workbench/activity/save.do".equals(path)){
            System.out.println("save保存方法开始");
            save(response,request);
        }else if("/workbench/activity/selectActivityList.do".equals(path)){
            System.out.println("分页查询+查询方法开始");
            selectBySize(request,response);
        }


    }

    private void selectBySize(HttpServletRequest request, HttpServletResponse response) {
        // 首先获取参数
        String pageSizeStr=request.getParameter("pageSize");
        String pageNoStr=request.getParameter("pageNo");
        String name=request.getParameter("name");
        String owner=request.getParameter("owner");
        String startDate=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
        int pageSize=Integer.parseInt(pageSizeStr);
        int pageNo=Integer.parseInt(pageNoStr);
        int skipCount=(pageNo-1)*pageSize;
        // 因为参数很多，如果直接丢给Service来处理会很麻烦
        // 所以有两种办法 1.打包成map 2.封装成一个对象 这里采用第一种方法
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("pageSize",pageSize);
        map.put("pageNo",pageNo);
        map.put("skipCount",skipCount);
        // 然后调用Service层中的方法
        ActivityService activityService= (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        // 这里需要分析从service和dao层中需要取出什么数据
        // 因为前端需要的json数据是:{"total":"","dataList":["",""]}
        // 所以我们要从service层拿到一个map或者vo对象来封装这些数据
        // 因为在这里做分页操作频繁，所以我们将这些数据封装为vo对象
        // 注意：VO对象是用来展示数据的，是从后端传送到前端的
        Paging paging=activityService.getActivityList(map);
        // 然后需要将VO对象转换为json数据来传送回前端
        PrintJson.printJsonObj(response,paging);



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

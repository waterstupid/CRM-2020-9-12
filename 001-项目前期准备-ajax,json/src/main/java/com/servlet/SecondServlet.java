package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecondServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println(request.getParameter("name"));
        // ""name":"张三","age":12"
        // String s="{\"name\":\"张三\",\"age\":12}";
        // 向浏览器输出一个json格式的对象
        String s="{\"s1\":{\"name\":\"zhangsan\",\"age\":\"12\"},\"s2\":{\"name\":\"lisi\"},\"age\":\"20\"}";
        out.print(s);
        // 关闭流
        out.close();
    }
}

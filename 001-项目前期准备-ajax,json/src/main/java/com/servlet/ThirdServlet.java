package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ThirdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 需要告诉浏览器传输的数据为json格式,并处理乱码格式
        response.setContentType("text/json;charset=utf-8");
        //response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print("{\"name\":\"张三\"}");
        out.close();
    }
}

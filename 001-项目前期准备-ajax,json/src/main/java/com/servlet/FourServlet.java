package com.servlet;

import com.domain.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FourServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String str="";
        Student student1 = new Student("张三", 12);
        Student lisi = new Student("李四", 13);
        str="[{\"name\":\"张三\",\"age\":\"12\"},{\"name\":\"李四\",\"age\":\"13\"}]";
        out.print(str);
        out.close();

    }
}

package com.xiaofu;

import com.xiaofu.bean.Student;
import com.xiaofu.service.StudentService;
import com.xiaofu.service.impl.StudentServiceImpl;
import com.xiaofu.util.ProxyUtil;
import org.junit.Test;

import java.util.List;

public class Text02 {
    @Test
    public void text(){
        StudentService studentService=new StudentServiceImpl();
        StudentService service = (StudentService) ProxyUtil.getProxy(studentService);
        System.out.println("service:"+service.getClass().getName());
        List<Student> list = service.selectAll();
        for (Student student : list) {
            System.out.println(student);
        }


    }
}

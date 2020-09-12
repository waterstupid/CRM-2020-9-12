package com.xiaofu;

import com.xiaofu.dao.StudentDao;
import com.xiaofu.bean.Student;
import com.xiaofu.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Text01 {
    @Test
    public void test(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> list = studentDao.selectAll();
        for (Student student : list) {
            System.out.println(student);
        }
    }


}

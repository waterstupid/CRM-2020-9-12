package com.xaofu.crm.setting;

import com.xiaofu.crm.settings.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import util.SqlSessionUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Test01 {
    @Test
    public void test(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Map<String,String> map=new HashMap<>();
        map.put("name","zs");
        map.put("password","202cb962ac59075b964b07152d234b70");
        System.out.println(userDao.login(map));
        /*String config="mybatis.xml";
        try {
            InputStream in = Resources.getResourceAsStream(config);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = sqlSessionFactory.openSession();
            UserDao userDao = session.getMapper(UserDao.class);
            Map<String,String> map=new HashMap<>();
            map.put("name","张三");
            map.put("password","202cb962ac59075b964b07152d234b70");
            System.out.println(userDao.login(map));

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}

package com.xiaofu.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;

public class SqlSessionUtil {
    private static SqlSession sqlSession;
    static{
        String config="mybatis.xml";
        try {
            InputStream in = Resources.getResourceAsStream(config);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getSqlSession(){
        return sqlSession;
    }

}

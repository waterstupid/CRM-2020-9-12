package com.xiaofu.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TransactionInvocation implements InvocationHandler {
    private Object target;

    public TransactionInvocation(Object target) {
        this.target = target;
    }

    // 该方法用于来添加事务
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        Object result = null;
        // 进行try..catch处理
        try{
            result = method.invoke(target,args);
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }finally{
            sqlSession.commit();

        }
        return result;
    }
}

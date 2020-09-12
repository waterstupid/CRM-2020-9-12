package com.xiaofu.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static Object getProxy(Object target){
        // 使用动态代理来得到代理对象
        InvocationHandler handler=new TransactionInvocation(target);
        Object object = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
        return object;
    }
}

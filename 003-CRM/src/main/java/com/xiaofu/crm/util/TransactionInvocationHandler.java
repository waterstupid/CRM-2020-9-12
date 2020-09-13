package com.xiaofu.crm.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;

public class TransactionInvocationHandler implements InvocationHandler{
	
	private Object target;
	
	public TransactionInvocationHandler(Object target){
		
		this.target = target;
		
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		SqlSession session = null;
		
		Object obj = null;
		
		try{
			session = SqlSessionUtil.getSqlSession();
			
			obj = method.invoke(target, args); 

			
			session.commit();
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
			
			//处理的是什么异常，继续往上抛什么异常
			// 如果这里没有这句话，那么程序就会发生错误
			// 因为我们在controller使用的是动态代理为我们生成的代理对象
			// 然后由代理对象帮助我们去调用service中的方法
			// 所以是controller--->代理类对象--->UserService
			// 所以如果在UserService中出现了异常，会被代理类对象捕捉到，而controller是捕捉不到这个
			// 异常的，所以要继续向上抛出该异常,需要继续向上抛出异常,controller才能接收到该异常
			 throw e.getCause();
		}finally{
			SqlSessionUtil.myClose(session);
		}
		
		return obj;



	}
	
	public Object getProxy(){
		
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
		
	}
	
}












































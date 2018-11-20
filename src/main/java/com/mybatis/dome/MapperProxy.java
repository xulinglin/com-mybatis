package com.mybatis.dome;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class MapperProxy implements InvocationHandler {

	private SqlSession sqlSessionProxy;

	private Class clazz;
    public MapperProxy(Class clazz,SqlSession sqlSessionProxy){
        this.sqlSessionProxy = sqlSessionProxy;
        this.clazz = clazz;
    }
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSessionProxy.execute(clazz.getName(), method.getName(), args);
	}

}

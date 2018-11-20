package com.mybatis.dome;

import java.lang.reflect.Proxy;

public class SqlSessionFactory {

	public <T>T getMapper(Class<T> clazz){
         //动态代理调用。
		 return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new MapperProxy(clazz,SqlSession.getSqlSession()));
    }
}

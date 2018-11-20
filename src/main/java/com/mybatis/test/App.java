package com.mybatis.test;

import com.mybatis.dao.UserMapper;
import com.mybatis.dome.SqlSessionFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException
    {
    	final SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
    	final UserMapper userMapper = sqlSessionFactory.getMapper(UserMapper.class);
    	long time = System.currentTimeMillis();
    	System.out.println(userMapper.selectByCondition("2"));
    	System.out.println((System.currentTimeMillis() - time)/1000d);
    	
    	for (int i = 0; i <= 10; i++) {
    		new Thread(new Runnable() {
    			
    			@Override
    			public void run() {
    				// TODO Auto-generated method stub
    				while(true) {
    					try {
    						Thread.sleep(500);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    					synchronized(sqlSessionFactory) {
    						long time = System.currentTimeMillis();
    						UserMapper userMapper1 = sqlSessionFactory.getMapper(UserMapper.class);
    						System.out.println(Thread.currentThread().getName()+userMapper1.selectByCondition("2"));
    				    	System.out.println((System.currentTimeMillis() - time)/1000d);
    					}
    				}
    			}
    		}).start();
		}
    	
    	
    	
  
    }
}

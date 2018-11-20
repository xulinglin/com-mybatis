package com.mybatis.dome;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SqlSession {

	private static SqlSession sqlSession;

	private Executor executor;

	private SqlSession() {
		// 获取执行器,负责SQL语句的生成和查询缓存的维护
		executor = Executor.getExecutor();
	}

	public synchronized static SqlSession getSqlSession() {
		if (sqlSession == null) {
			sqlSession = new SqlSession();
		}
		return sqlSession;
	}

	private volatile static String user = "root";

	private volatile static String password = "sasa";

	private final static String url = "jdbc:mysql:///test?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf8";

	private volatile static String jdbcDriver = "com.mysql.jdbc.Driver";

	@SuppressWarnings("unchecked")
	public <T> T execute(String className, String name, Object... parameter) throws Exception {
		AnalysisMapper analysisMapper = executor.selectOne(className, name, parameter);
		Class clazz = Class.forName(analysisMapper.getResultType());
		// 1.加载驱动
		Class.forName(jdbcDriver);
		// 获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		ResultSet rs = null;
		Statement st = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(analysisMapper.getSql());
			ResultSetMetaData rsmd = rs.getMetaData();
			int valueNum = rsmd.getColumnCount();
			Field[] fields = clazz.getDeclaredFields();
			
			while (rs.next()) {// 对每一条记录进行操作
				Map<String, Object> map = new HashMap<>();
				Object obj = clazz.newInstance();// 构造业务对象实体
				// 将每一个字段取出进行赋值
				for (int i = 1; i <= valueNum; i++) {
					Object value = null;
					String key = rsmd.getColumnName(i);
					String type = rsmd.getColumnTypeName(i);
					if("BIGINT".equals(type)) {value = rs.getInt(key);}
					if("VARCHAR".equals(type)) {value = rs.getString(key);}
					if("DATETIME".equals(type)) {value = rs.getDate(key);}
					// 寻找该列对应的对象属性
					map.put(camelCaseName(key), value);
				}
				for (int j = 0; j < fields.length; j++) {
					Field f = fields[j];
					// 如果匹配进行赋值
					String fName = f.getName();
					if (map.containsKey(f.getName())) {
						boolean flag = f.isAccessible();
						f.setAccessible(true);
						Object v = map.get(fName);
						f.set(obj, v);
						f.setAccessible(flag);
					}
				}
				return (T) obj;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 6.关闭链接，释放资源
			rs.close();
			st.close();
			conn.close();
		}
		return null;
	}
	/** 
     * 转换为驼峰 
     *  
     * @param name 
     * @return 
     */  
    public static String camelCaseName(String name) {  
        StringBuilder result = new StringBuilder();  
        if (name != null && name.length() > 0) {  
            boolean flag = false;  
            for (int i = 0; i < name.length(); i++) {  
                char ch = name.charAt(i);  
                if ("_".charAt(0) == ch) {  
                    flag = true;  
                } else {  
                    if (flag) {  
                        result.append(Character.toUpperCase(ch));  
                        flag = false;  
                    } else {  
                        result.append(ch);  
                    }  
                }  
            }  
        }  
        return result.toString();  
    }  
}

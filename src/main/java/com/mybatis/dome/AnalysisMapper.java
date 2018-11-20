package com.mybatis.dome;

import com.mybatis.enums.SqlEnum;

public class AnalysisMapper {
	
	private String id;
	private String parameterType;
	private String resultType;
	private String sql;
	private SqlEnum sqlType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public SqlEnum getSqlType() {
		return sqlType;
	}
	public void setSqlType(SqlEnum sqlType) {
		this.sqlType = sqlType;
	}
	@Override
	public String toString() {
		return "Mapper [id=" + id + ", parameterType=" + parameterType + ", resultType=" + resultType + ", sql=" + sql
				+ ", sqlType=" + sqlType + "]";
	}

	
	
}

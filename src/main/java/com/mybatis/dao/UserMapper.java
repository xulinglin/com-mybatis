package com.mybatis.dao;

import com.mybatis.dome.Param;
import com.mybatis.model.User;

public interface UserMapper {
	User selectByCondition(@Param("id")String id);
}

package com.mybatis.Mapper;

import java.util.List;

import com.mybatis.model.User;

public interface UserMapper {
	List<User> selectByCondition();
}

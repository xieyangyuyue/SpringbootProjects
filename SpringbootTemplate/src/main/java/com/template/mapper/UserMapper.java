package com.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.template.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectByUsername(String username);
}


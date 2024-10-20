package com.template.mapper;

import com.template.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 86187
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2024-10-18 10:12:01
 * @Entity com.template.pojo.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectByUsername(String username);
}





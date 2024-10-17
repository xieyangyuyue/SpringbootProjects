package com.training.mapper;

import com.training.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 86187
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-08-10 14:00:26
* @Entity com.training.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> listAll();
}





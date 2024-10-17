package com.training.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.training.pojo.User;
import com.training.service.UserService;
import com.training.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 86187
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-08-10 14:00:26
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> listAll() {
        return userMapper.listAll();
    }
}





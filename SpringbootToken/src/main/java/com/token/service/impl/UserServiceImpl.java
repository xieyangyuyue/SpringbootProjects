package com.token.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.token.pojo.User;
import com.token.service.UserService;
import com.token.mapper.UserMapper;
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





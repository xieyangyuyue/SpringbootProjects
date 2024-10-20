package com.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.template.mapper.UserMapper;
import com.template.pojo.User;
import com.template.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 86187
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-10-18 10:12:01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}





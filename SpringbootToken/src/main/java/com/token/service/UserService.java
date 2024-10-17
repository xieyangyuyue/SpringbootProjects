package com.training.service;

import com.training.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86187
* @description 针对表【user】的数据库操作Service
* @createDate 2024-08-10 14:00:26
*/
public interface UserService extends IService<User> {

    List<User> listAll();
}

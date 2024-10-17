package com.template.controller;

import com.template.pojo.User;
import com.template.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
	 @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    //表单新增 将表单数据写入数据库
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return userService.save(user) ? Result.success() : Result.fail();
    }

    //更新
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return userService.updateById(user) ? Result.success() : Result.fail();
    }

    //删除
    @GetMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        return userService.removeById(id) ? Result.success() : Result.fail();
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody User user) {
        return userService.saveOrUpdate(user) ? Result.success() : Result.fail();
    }

}

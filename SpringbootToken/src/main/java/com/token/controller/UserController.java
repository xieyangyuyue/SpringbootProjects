package com.training.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.training.common.QueryPageParam;
import com.training.pojo.Result;
import com.training.pojo.User;
import com.training.pojo.Menu;
import com.training.service.MenuService;
import com.training.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;
	 @GetMapping("/hello")
    public string hello() {
        return "hello";
    }
    //返回所有数据
    @GetMapping("/list")
    public List<User> list() {
        return userService.listAll();
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





    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List<User> list = userService.lambdaQuery()
                .eq(User::getNo, user.getNo())
                .eq(User::getPassword, user.getPassword())
                .list();
        if (!list.isEmpty()) {
            User user1 = list.get(0);
            List<Menu> menuList = menuService.lambdaQuery()
                    .like(Menu::getMenuright, user1.getRoleId())
                    .list();
            HashMap<String, Object> res = new HashMap<>();
            res.put("user", user1);
            res.put("menu", menuList);
            return Result.success(res);
        }
        return Result.fail();
    }


    //根据no查询是否有重复用户名
    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no) {
        List list = userService.lambdaQuery().eq(User::getNo, no).list();
        return !list.isEmpty() ? Result.success(list) : Result.fail();
    }


    /**
     * 查询（模糊，精确） like eq
     */

    @PostMapping("/listP")
    public Result listP(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //不为空
        if (StringUtils.isNotBlank(user.getName())) {
            lambdaQueryWrapper.like(User::getName, user.getName());
        }
        return Result.success(userService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam queryPageParam) {
        //打印其他信息
        HashMap param = queryPageParam.getParam();
        //从前端传来的param的  name  sex  进行 lambdaQueryWrapper
        String name = (String) param.get("name");
        String sex = (String) param.get("sex");
        String roleId = (String) param.get("roleId");

        /**
         * setCurrent 设置 页数
         * setSize 设置条数
         */
        Page<User> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        //按照名字查询匹配
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 判断是否为空
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(User::getName, name);
        }
        // 判断是否为空
        if (StringUtils.isNotBlank(sex) && !"null".equals(sex)) {
            lambdaQueryWrapper.like(User::getSex, sex);
        }
        if (StringUtils.isNotBlank(roleId)) {
            lambdaQueryWrapper.eq(User::getRoleId, roleId);
        }
        //封装查询结果
        IPage<User> result = userService.page(page, lambdaQueryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }
}

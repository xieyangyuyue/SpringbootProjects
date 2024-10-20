package com.tokentemplate.Controller;

import com.tokentemplate.Utils.TokenUtil;
import com.tokentemplate.pojo.LoginUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TokenTestController {
    @Autowired
    TokenUtil tokenUtil;
    @PostMapping("/login")
    public String login(@RequestBody LoginUser user){
        // 先验证用户的账号密码,账号密码验证通过之后，生成Token
        String role = "ROLE_ADMIN";
        String token = tokenUtil.getToken(user.getUsername(), role);
        return token;
    }
    @PostMapping("/testToken")
    public String testToken(HttpServletRequest request){
        String token = request.getHeader("token");
        Map token1=tokenUtil.parseToken(token);
        System.out.println(tokenUtil.getTokenUserName(token1));
        return "请求成功";
    }
}
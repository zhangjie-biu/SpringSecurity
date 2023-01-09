package com.zhangjie.controller;

import com.zhangjie.domain.ResponseResult;
import com.zhangjie.domain.User;
import com.zhangjie.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;





    @RequestMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录

        return loginService.login(user);

    }

    @RequestMapping("/user/logout")
    public ResponseResult  logout(){
        return loginService.logout();
    }
}

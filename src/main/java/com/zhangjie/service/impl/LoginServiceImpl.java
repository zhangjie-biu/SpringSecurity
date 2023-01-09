package com.zhangjie.service.impl;

import com.zhangjie.domain.LoginUser;
import com.zhangjie.domain.ResponseResult;
import com.zhangjie.domain.User;
import com.zhangjie.service.LoginService;
import com.zhangjie.utils.JwtUtil;
import com.zhangjie.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {



        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authentication =  authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //如果认证没通过，给出对应提示
        if(Objects.isNull(authentication)){
            throw new RuntimeException("登陆失败");
        }
        //如果认证通过了生成一个jwt

        LoginUser principal = (LoginUser) authentication.getPrincipal();
        String userid = principal.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);

        Map map = new HashMap<String,String>();
        map.put("token",jwt);

        //把完整的用户信息存入redis userid作为key
        redisCache.setCacheObject("login:"+userid,principal);


        return new ResponseResult(200,"登陆成功",map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:"+userId.toString());
        return new ResponseResult(200,"注销成功");
    }
}

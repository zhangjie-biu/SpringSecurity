package com.zhangjie.service;


import com.zhangjie.domain.ResponseResult;
import com.zhangjie.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}

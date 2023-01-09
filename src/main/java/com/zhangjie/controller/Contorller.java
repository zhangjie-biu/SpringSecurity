package com.zhangjie.controller;


import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Contorller {


    @RequestMapping("/hello")
    //使用官方提供的鉴权方法
    //@PreAuthorize("hasAuthority('system:dept:list')")
    //使用自定义鉴权方法
    @PreAuthorize("@zj.hasAuthority('system:dept:list')")
    public String hello(){
        return "hello";
    }
}

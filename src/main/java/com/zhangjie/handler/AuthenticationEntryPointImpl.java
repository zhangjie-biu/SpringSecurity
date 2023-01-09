package com.zhangjie.handler;

import com.alibaba.fastjson.JSON;
import com.zhangjie.domain.ResponseResult;
import com.zhangjie.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult(HttpStatus.FORBIDDEN.value(),"用户认证失败，请重新登录");
        String json = JSON.toJSONString(responseResult);
        //处理异常
        WebUtils.renderString(httpServletResponse,json);
    }
}

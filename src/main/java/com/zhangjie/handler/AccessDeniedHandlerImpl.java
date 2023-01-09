package com.zhangjie.handler;

import com.alibaba.fastjson.JSON;
import com.zhangjie.domain.ResponseResult;
import com.zhangjie.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户授权失败，您的权限不足");
        String json = JSON.toJSONString(responseResult);
        //处理异常
        WebUtils.renderString(httpServletResponse,json);
    }
}

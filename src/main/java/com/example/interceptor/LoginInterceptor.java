package com.example.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {//处理器拦截器，拦截组件
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取header中的参数
        String token = request.getHeader("Authorization")==null? "":request.getHeader("Authorization");
        //System.out.println("token:为"+token);
        if(token.equals("") || token == null){
            if(request.getSession().getAttribute("user") != null){
                //System.out.println("走数据库---------------------");
                return true;
            }else{
                //System.out.println("无session无token返回---------------------");
                response.sendRedirect("/admin");
                return false;
            }
        }else{
            String userJson = redisTemplate.opsForValue().get(token);
            User user = JSON.parseObject(userJson, User.class);
            //System.out.println("user:" + user);
            if(user != null){
                request.getSession().setAttribute("user",user);
            }
            //System.out.println("走redis---------------------");
            return true;
        }
    }
}

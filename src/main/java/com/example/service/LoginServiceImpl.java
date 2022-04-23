package com.example.service;

import com.alibaba.fastjson.JSON;
import com.example.po.User;
import com.example.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wxy
 * @date 2022/4/17 11:22
 */

@Service
public class LoginServiceImpl implements LoginService {

    private static final String slat = "mszlu!@#";
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Result login(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        String pwd = DigestUtils.md5Hex(password + slat);
        User user = userService.checkUser(username,pwd);
        if (user == null){
            Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        LoginUserVo userVo = new LoginUserVo();
        userVo.setId(user.getId());
        userVo.setAvatar(user.getAvatar());
        userVo.setNickname(user.getNickname());
        userVo.setEmail(user.getEmail());
        userVo.setType(user.getType());
        userVo.setUsername(user.getUsername());
        userVo.setUpdateTime(user.getUpdateTime());
        userVo.setPassword(user.getPassword());
        userVo.setCreateTime(user.getCreateTime());
        //登录成功，使用JWT生成token，返回token和redis中
        String token = JWTUtils.createToken(user.getId());
        System.out.println("jsonToken:"+JSON.toJSONString(userVo));
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(userVo),1, TimeUnit.DAYS);
        //System.out.println("TOKEN_:"+new ArrayList<>(redisTemplate.keys("TOKEN")).get(0));
        return Result.success(token,user);
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("123456"+slat));
    }
}


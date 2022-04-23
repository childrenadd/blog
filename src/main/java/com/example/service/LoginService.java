package com.example.service;


import com.example.util.LoginParam;
import com.example.util.Result;

/**
 * @author wxy
 * @date 2022/4/17 11:21
 */
public interface LoginService {
    Result login(LoginParam loginParam);
}

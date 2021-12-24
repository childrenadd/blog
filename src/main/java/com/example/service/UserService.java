package com.example.service;

import com.example.po.User;

public interface UserService {
    User checkUser(String username,String password);
}

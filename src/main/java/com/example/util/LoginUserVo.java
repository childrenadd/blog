package com.example.util;

import lombok.Data;

import java.util.Date;

/**
 * @author wxy
 * @date 2022/4/17 12:46
 */
@Data
public class LoginUserVo {

    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;

}


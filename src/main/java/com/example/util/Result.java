package com.example.util;

import com.example.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxy
 * @date 2022/4/17 11:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private boolean success;

    private Integer code;

    private String msg;

    private Object data;

    private User user;

    public static Result success(Object data,User user) {
        return new Result(true,200,"success",data,user);
    }
    public static Result fail(Integer code, String msg) {
        return new Result(false,code,msg,null,null);
    }
    /*public static Integer success(Object data) {
        return 200;
    }
    public static Integer fail(Integer code, String msg) {
        return 500;
    }*/
}

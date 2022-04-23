package com.example.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wxy
 * @date 2022/4/17 10:33
 */
public class JWTUtils {
    private static final String jwtToken = "54188wxy";
    public static String createToken(Long userId){
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,jwtToken)//签发算法，密钥为jwtToken
                .setClaims(claims)//body数据，唯一
                .setIssuedAt(new Date())//设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 25*60*60*60*1000));//过期时间
        String token = jwtBuilder.compact();
        return token;
    }
    public static Map<String,Object> checkToken(String token){
        try{
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String,Object>)parse.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

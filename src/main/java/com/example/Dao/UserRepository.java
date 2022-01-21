package com.example.Dao;

import com.example.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wxy
 * @date 2021/5/25 15:21
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}

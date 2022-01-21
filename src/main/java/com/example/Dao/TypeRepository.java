package com.example.Dao;

import com.example.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wxy
 * @date 2021/5/25 19:21
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
    @Query("select t from t_type t")
    List<Type> findTop(Pageable pageable);
}

package com.example.Dao;

import com.example.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wxy
 * @date 2021/5/24 21:10
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
    @Query("select t from t_tag t")
    List<Tag> findTop(Pageable pageable);
}

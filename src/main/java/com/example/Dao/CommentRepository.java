package com.example.Dao;

import com.example.po.Commend;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wxy
 * @date 2021/5/24 20:23
 */
public interface CommentRepository extends JpaRepository<Commend,Long> {
    List<Commend> findByBlogIdAndParentCommendNull(Long blogId, Sort sort);
}

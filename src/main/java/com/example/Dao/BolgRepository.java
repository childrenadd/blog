package com.example.Dao;

import com.example.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wxy
 * @date 2021/5/24 21:00
 */
public interface BolgRepository extends JpaRepository<Blog,Long> , JpaSpecificationExecutor<Blog> {
    @Query("select t from t_blog t where t.recommend = true")
    List<Blog> findTop(Pageable pageable);//最新推荐排序
    @Query("select b from t_blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query,Pageable pageable);//全局搜索

    @Transactional
    @Modifying
    @Query("update t_blog b set b.views = b.views+1 where b.id = ?1")//更新view
    int updateViews(Long id);

    @Query("select function('date_format',b.updateTime,'%Y') from t_blog b group by function('date_format',b.updateTime,'%Y') order by function('date_format',b.updateTime,'%Y') desc")
    List<String> findGroupYear();
    @Query("select b from t_blog b where function('date_format',b.updateTime,'%Y')=?1 order by b.updateTime desc")
    List<Blog> findByYear(String year);
}

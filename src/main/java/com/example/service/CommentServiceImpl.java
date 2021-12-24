package com.example.service;

import com.example.Dao.BolgRepository;
import com.example.Dao.CommentRepository;

import com.example.po.Commend;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BolgRepository bolgRepository;

    @Override
    public List<Commend> listCommentByBlogId(Long blogId) {  //查询父评论
        //没有父节点的默认为-1
        Sort sort = Sort.by("createTime");
        List<Commend> commends = commentRepository.findByBlogIdAndParentCommendNull(blogId,sort);
        return eachComment(commends);
    }
    @Transactional
    @Override
    //接收回复的表单
    public Commend saveComment(Commend commend) {
        //获得父id
        Long parentCommendId = commend.getParentCommend().getId();
        //没有父级评论默认是-1
        if (parentCommendId != -1) {
            //有父级评论
            commend.setParentCommend(commentRepository.getOne(parentCommendId));
        } else {
            commend.setParentCommend(null);
        }
        commend.setCreateTime(new Date());
        return commentRepository.save(commend);
    }
    //循环每个顶级评论节点
    private List<Commend> eachComment (List<Commend> commends)
    {
        List<Commend> commendsView = new ArrayList<>();
        for(Commend commend : commends){
            Commend c = new Commend();
            BeanUtils.copyProperties(commend,c);
            commendsView.add(c);
        }
        combineChildren(commendsView);
        return commendsView;
    }
    private void combineChildren(List<Commend> commends){
        for(Commend commend:commends){
            List<Commend> replys1 = commend.getReplyCommends();//得到所有回复评论子节点
            for(Commend reply1:replys1){
                recursively(reply1);
            }
            commend.setReplyCommends(tempReplys);
            tempReplys = new ArrayList<>();
        }
    }
    private List<Commend> tempReplys=new ArrayList<>();//子代集合
    //递归迭代
    private void recursively(Commend commend){
        tempReplys.add(commend);
        if(commend.getReplyCommends().size()>0){
            List<Commend> replys = commend.getReplyCommends();
            for(Commend reply : replys){
                tempReplys.add(reply);
                if(reply.getReplyCommends().size()>0){
                    recursively(reply);
                }
            }
        }
    }
}

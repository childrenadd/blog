package com.example.service;


import com.example.po.Commend;

import java.util.List;

public interface CommentService {
    List<Commend> listCommentByBlogId(Long blogId);

    Commend saveComment(Commend commend);
}

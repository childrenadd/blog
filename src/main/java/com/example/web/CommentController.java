package com.example.web;


import com.example.po.Commend;
import com.example.po.User;
import com.example.service.BlogService;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;
    @Value("${commend.avatar}")
    private String avatar;


    @GetMapping("/commends/{blogId}")  //展示留言
    public String commends(@PathVariable Long blogId, Model model){
        model.addAttribute("commends", commentService.listCommentByBlogId(blogId));
        return "blog :: commendList";
    }

    @PostMapping ("/commends")   //提交留言
    public String post(Commend commend, HttpSession session){
        Long blogId = commend.getBlog().getId();
        commend.setBlog(blogService.getBlog(blogId));  //绑定博客与评论
        commend.setAvatar(avatar);
        User user = (User) session.getAttribute("user");
        if (user != null){   //用户为管理员
            commend.setAvatar(user.getAvatar());
            commend.setAdminCommend(true);
        }else {
            commend.setAvatar(avatar);
        }
        System.out.println(commend);
        commentService.saveComment(commend);
        return "redirect:/commends/" + blogId;
    }
}

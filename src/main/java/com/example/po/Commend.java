package com.example.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wxy
 * @date 2021/5/27 08:53
 */
@Entity(name = "t_commend")
@Table
public class Commend {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String content;
    private String avatar;
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @ManyToOne
    private Blog blog;
    @OneToMany(mappedBy = "parentCommend")
    private List<Commend> replyCommends = new ArrayList<>();
    @ManyToOne
    private Commend parentCommend;

    private boolean adminCommend;

    public Commend() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Commend> getReplyCommends() {
        return replyCommends;
    }

    public void setReplyCommends(List<Commend> replyCommends) {
        this.replyCommends = replyCommends;
    }

    public Commend getParentCommend() {
        return parentCommend;
    }

    public void setParentCommend(Commend parentCommend) {
        this.parentCommend = parentCommend;
    }

    public boolean isAdminCommend() {
        return adminCommend;
    }

    public void setAdminCommend(boolean adminCommend) {
        this.adminCommend = adminCommend;
    }


}

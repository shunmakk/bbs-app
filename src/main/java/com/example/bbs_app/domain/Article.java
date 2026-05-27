package com.example.bbs_app.domain;

import java.util.List;

/**
 * 記事投稿のドメイン.
 */
public class Article {
    /**
     * 投稿id
     */
    private Integer id;
    /**
     * 投稿者名
     */
    private String name;
    /**
     * 記事内容
     */
    private String comment;
    /**
     * コメント一覧
     */
    private List<Comment> commentList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", commentList=" + commentList +
                '}';
    }
}

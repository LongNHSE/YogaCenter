/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dto;

import java.sql.Date;

/**
 *
 * @author devli
 */
public class CommentDTO {
    private String commentID;
    private String commentContent;
    private BlogDTO blogID;
    private Date createDate;

    public CommentDTO() {
    }

    public CommentDTO(String commentID, String commentContent, BlogDTO blogID, Date createDate) {
        this.commentID = commentID;
        this.commentContent = commentContent;
        this.blogID = blogID;
        this.createDate = createDate;
    }



    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public BlogDTO getBlogID() {
        return blogID;
    }

    public void setBlogID(BlogDTO blogID) {
        this.blogID = blogID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "CommentDTO{" + "commentID=" + commentID + ", commentContent=" + commentContent + ", blogID=" + blogID + ", createDate=" + createDate + '}';
    }

    

 
}

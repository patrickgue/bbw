/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.database.hibernate;

import ch.bbw.cms.models.Comment;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author guenthard
 */
@Entity
@Table
public class DBComment {
    @Id
    @GeneratedValue
    private Integer comment_id;
    
    private String comment_content;
    private Integer comment_user_id;
    private Integer comment_post_id;
    private Date comment_date;
    
    public DBComment(){}
    
    public DBComment(Integer id, String content, Integer userId, Date date){
        this.comment_id = id;
        this.comment_content = content;
        this.comment_user_id = userId;
        this.comment_date = date;
    }
    
    public Comment toComment(){
        return new Comment(getId(), getContent(), getPostId(), getUserId(), getDate());
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return comment_id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.comment_id = id;
    }

   

    /**
     * @return the content
     */
    public String getContent() {
        return comment_content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.comment_content = content;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return comment_user_id;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.comment_user_id = userId;
    }

    /**
     * @return the postId
     */
    public Integer getPostId() {
        return comment_post_id;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(Integer postId) {
        this.comment_post_id = postId;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return comment_date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.comment_date = date;
    }
    
}

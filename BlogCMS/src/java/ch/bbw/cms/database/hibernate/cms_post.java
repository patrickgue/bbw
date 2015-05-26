/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.database.hibernate;

import ch.bbw.cms.models.Post;
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
public class cms_post {
    @Id
    @GeneratedValue
    private Integer post_id;
    
    private String post_title;
    private String post_content;
    private Integer post_likes;
    private Integer post_user_id;
    private Date post_date;
    
    public cms_post(){}
    
    public cms_post(Integer id, String title, String content, Integer likes, Integer userId, Date date){
        this.post_id = id;
        this.post_title = title;
        this.post_content = content;
        this.post_likes = likes;
        this.post_user_id = userId;
        this.post_date = date;
    }
    
    public Post toPost(){
        return new Post(post_user_id, post_title, post_content, post_user_id, post_date);
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return post_id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.post_id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return post_title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.post_title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return post_content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.post_content = content;
    }

    /**
     * @return the likes
     */
    public Integer getLikes() {
        return post_likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(Integer likes) {
        this.post_likes = likes;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return post_user_id;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.post_user_id = userId;
    }
}

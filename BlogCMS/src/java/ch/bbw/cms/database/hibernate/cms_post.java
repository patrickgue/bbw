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
    private Integer id;
    
    private String title;
    private String content;
    private Integer likes;
    private Integer userId;
    private Date date;
    
    public cms_post(){}
    
    public cms_post(Integer id, String title, String content, Integer likes, Integer userId, Date date){
        this.id = id;
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.userId = userId;
        this.date = date;
    }
    
    public Post toPost(){
        return new Post(userId, title, content, userId, date);
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the likes
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

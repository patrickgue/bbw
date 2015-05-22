/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.database.hibernate;

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
public class cms_pinwall {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer postId;
    private Integer userId;
    
    public cms_pinwall(){}
    
    public cms_pinwall(Integer id, Integer postId, Integer userId){
        this.id = id;
        this.postId = postId;
        this.userId = userId;
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
     * @return the postId
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
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

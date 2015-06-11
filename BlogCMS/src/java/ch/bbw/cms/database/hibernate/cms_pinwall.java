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
    private Integer pin_id;
    private Integer pin_post_id;
    private Integer pin_user_id;
    
    public cms_pinwall(){}
    
    public cms_pinwall(Integer id, Integer postId, Integer userId){
        this.pin_id = id;
        this.pin_post_id = postId;
        this.pin_user_id = userId;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return pin_id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.pin_id = id;
    }

    /**
     * @return the postId
     */
    public Integer getPostId() {
        return pin_post_id;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(Integer postId) {
        this.pin_post_id = postId;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return pin_user_id;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.pin_user_id = userId;
    }
}

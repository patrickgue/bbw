/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.bbw.cms.models;

import java.util.Date;

/**
 * This class is used to save comments belonging to a post
 * @author 5ia13paguenthard
 * @author 5ia13nosiegrist
 */
public class Comment extends Content{

    int postId;
    
    public Comment(Integer id, String content, int postId, int userId, Date date){
        super(id, userId , content, date);
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postID) {
        this.postId = postID;
    }
    
}

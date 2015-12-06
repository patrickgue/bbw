/* 
 * @author: 5ia13paguenthard
 * @author: 5ia13nosiegrist
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.models;

import java.util.Date;

/**
 * This class is used to save comments belonging to a post
 */
public class Comment extends Content{

    int postId;
    
    public Comment(Integer id, String content, int postId, int userId, Date date){
        super(id, userId , content, date);
        this.postId = postId;
    }
    
    public Comment(Integer id, String content, int postId, User user, Date date){
        super(id, user , content, date);
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postID) {
        this.postId = postID;
    }
    
    public int getCommentId(){
        return super.getContentId();
    }
    
    public void setCommentId(int commentid){
        super.setContentId(commentid);
    }
}

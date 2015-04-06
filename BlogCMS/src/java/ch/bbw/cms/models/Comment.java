/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.bbw.cms.models;

/**
 *
 * @author 5ia13nosiegrist
 */
public class Comment {
    int commentID;
    String CommentContent;
    int PostID;
    

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String CommentContent) {
        this.CommentContent = CommentContent;
    }

    public int getPostID() {
        return PostID;
    }

    public void setPostID(int PostID) {
        this.PostID = PostID;
    }
    
    
    
}

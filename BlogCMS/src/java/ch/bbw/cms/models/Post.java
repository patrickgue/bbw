/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.bbw.cms.models;

import java.util.ArrayList;

/**
 *
 * @author 5ia13nosiegrist
 */
public class Post extends Content{
    private String title;
    private ArrayList<Comment> comments;
    private ArrayList<User> likes;

    public Post(int userId, String content, String title) {
        super(userId, content);
        this.title = title;
        this.comments = new ArrayList<Comment>();
        this.likes = new ArrayList<User>();
    }
    public void like(User user){
        for(User tmp : likes){
	    if(tmp.getUserId() == user.getUserId){
		// User already defined 
		// TODO error message?
		return;
	    }
	}
	likes.add(user);
    }
    
    
    public void dislike(User user){
        likes.remove(user);
    }
    
    public int countLikes(){
        return likes.size();
    }
    
    public boolean hasLiked(User user){
        try{
            int i = likes.indexOf(user);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}

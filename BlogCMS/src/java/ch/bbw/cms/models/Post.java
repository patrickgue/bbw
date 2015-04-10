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
    
    @Override
    public String toString(){
        return "Post: "+title+"\nContent:\n"+getContent();
    }
    
    public void like(User user){
        for(User tmp : getLikes()){
	    if(tmp.getUserId() == user.getUserId()){
		// User already defined 
		// TODO error message?
		return;
	    }
	}
	getLikes().add(user);
    }
    
    
    public void dislike(User user){
        getLikes().remove(user);
    }
    
    public int countLikes(){
        return getLikes().size();
    }
    
    public boolean hasLiked(User user){
        try{
            int i = getLikes().indexOf(user);
            return true;
        }catch(Exception e){
            return false;
        }
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
     * @return the comments
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getSummary(){
        return getContent().substring(0, (getContent().length() > 20 ? 20 : getContent().length()) )+(getContent().length() > 20 ? "..." : "" );
    }
    
    /**
     * @param comments the comments to set
     */
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    /**
     * @return the likes
     */
    public ArrayList<User> getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(ArrayList<User> likes) {
        this.likes = likes;
    }
}

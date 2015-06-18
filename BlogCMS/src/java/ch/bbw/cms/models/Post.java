/* 
 * @author: 5ia13paguenthard
 * @author: 5ia13nosiegrist
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.models;

import ch.bbw.cms.inf.Log;
import ch.bbw.cms.mock.DefaultLog;
import java.util.ArrayList;
import java.util.Date;

/**
 * Used to save a simple post
 */
public class Post extends Content{
    private String title;
    private ArrayList<Comment> comments;
    private ArrayList<User> likes;
    private Log logger = new DefaultLog();

    public Post(Integer postId, String title, String content, int userId, Date date) {
        super(postId, userId, content, date);
        this.title = title;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
    }
    
    public Post(Integer postId, String title, String content, User user, Date date) {
        super(postId, user, content, date);
        this.title = title;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
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
        return getContent().replaceAll("<[^>]+>", "").substring(0, (getContent().length() > 20 ? 20 : getContent().length()) )+(getContent().length() > 20 ? "..." : "" );
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
    
    public void setPostId(int id){
        this.setContentId(id);
    }
    
    public int getPostId(){
        return this.getContentId();
    }
}

/**
 * @author: 5ia13paguenthard
 * @author: 5ia13nosiegrist
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.database.Database;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.helper.ClosedList;
import ch.bbw.cms.helper.Const;
import ch.bbw.cms.models.Comment;
import ch.bbw.cms.models.User;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.*;


/**
 * <b>IndexBean</b>
 * <p>IndexBean is the main Bean in this project. It handles all the functionality of the main
 * page and also provides methods and attributes for other pages.
 * @author patrick
 */
@ManagedBean
@SessionScoped
public class IndexBean extends AllPageBean{

    private ArrayList<Post> postList;
    private String search = "Search Posts";
    private Post currentPost;
    private int currentPin;
    private String comment;
    private ArrayList<Comment> commentList;
    
    
    
    

    
    public IndexBean() {
	comment = "Enter comment";
        
        
        if(Const.LOG_DEBUG){
            logger.debug("PostList: "+postList);
        }
    }
    
    public String postComment(){
        Date d = new Date();
        getDatabase().addComment(new Comment(1, comment, currentPost.getPostId(), getSessiondata().getUserId(), d));
        return "main.xhtml";
    }
    
    public ArrayList<Post> getPostList(){ 
        if(search.equals("") || search.equals("Search")){
            refreshPostList();
        }
        return postList;
    }
    
    public void setPostList(ArrayList<Post> postList){
        this.postList = postList;
    }
    
    public void refreshPostList(){
        if(Const.LOG_DEBUG){
            logger.debug("Refresh");
        }
        postList = getDatabase().getPosts();
    }


    

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }

    public String performSearch(){
        setPostList(getDatabase().getPosts(search));
        if(Const.LOG_DEBUG){
            logger.debug(search+", "+postList.toString());
        }
        return "main.xhtml";
    }
    
    /**
     * @return the currentPost
     */
    public Post getCurrentPost() {
        return currentPost;
    }

    /**
     * @param currentPost the currentPost to set
     */
    public void setCurrentPost(Post currentPost) {
        this.currentPost = currentPost;
    }

    

    
    public String gotoNewPost(){
        getSessiondata().setCurrentPostId(-1);
        return "create.xhtml";
    }
    
    public String editPost(){
        getSessiondata().setCurrentPostId(currentPost.getPostId());
        return "create.xhtml";
    }

    public boolean editPostEnabled(){
        try{
            return currentPost.getUserId() == getSessiondata().getUserId();
        } catch(NullPointerException ex){
            return false;
        }
    }
    
    public boolean renderPin(){
        if(Const.LOG_DEBUG){
            logger.debug(""+getSessiondata().getUser());
        }
        
        try{
            currentPin = getDatabase().getPinId(getSessiondata().getUser().getUserId(), currentPost.getPostId());
        } catch(NullPointerException ex){
            logger.error("User is null", ex);
        }
        return currentPin == -1;
    }
    
    public boolean renderUnpin(){
        return !renderPin();
    }
    
    public String pinPost(){
        getDatabase().addPostToPinwall(getSessiondata().getUserId(), currentPost.getPostId());
        return "main.xhtml";
    }
    
    public String unpinPost(){
        getDatabase().deletePostFromPinwall(currentPin);
        return "main.xhtml";
    }
    
    public boolean isEditor(){
        try{
            return getSessiondata().getUser().getType().equals(UserType.content);
        } catch(Exception ex){
            return false;
        }
    }
    
    public String getUserNameFormPost(){
        return "";
    }

    /**
     * @return the database
     */
    @Override
    public Database getDatabase() {
        return super.getDatabase();
    }

    /**
     * @param database the database to set
     */
    @Override
    public void setDatabase(Database database) {
        super.setDatabase(database);
        if(postList == null){
            postList = database.getPosts();
        }
        
        try{
	    currentPost = postList.get(0);
	} catch(IndexOutOfBoundsException ex){
	    currentPost = new Post(0, "Ask the admin to get a creator account to publish your own blog!",  "no Posts", -1, new Date());
	}
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the commentList
     */
    public ArrayList<Comment> getCommentList() {
        this.commentList = getDatabase().getComments(currentPost.getPostId());
        return commentList;
    }

    /**
     * @param commentList the commentList to set
     */
    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }
  
}

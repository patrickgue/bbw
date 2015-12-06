/**
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.database.Database;
import javax.faces.bean.*;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.models.Post;
import java.util.Date;


/**
 *
 * @author patrick
 */
@ManagedBean
@RequestScoped
public class CreationBean extends AllPageBean{
    private String title;
    private String postcontent;
    private String userIdTest;
   
    private boolean showPostButton = true;
    private boolean showUpdateButton = false;
    
    
    public CreationBean(){
        
        
        
        
    }

    public String createPost(){
        int userid = getSessiondata().getUserId();
        
        if(getDatabase().createPost(title, postcontent,userid, new Date())){
            return "main.xhtml";
        } else {
            return "create.xhtml";
        }
    }
    
    public String updatePost(){
       
        if(getDatabase().updatePost(getSessiondata().getCurrentPostId(), title, postcontent)){
            return "main.xhtml";
        } else {
            return "create.xhtml";
        }
    }
    
    public String deletePost(){
        if(getDatabase().deletePost(getSessiondata().getCurrentPostId())){
            return "main.xhtml";
        } else {
            return "create.xhtml";
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
     * @return the postcontent
     */
    public String getPostcontent() {
        return postcontent;
    }

    /**
     * @param postcontent the postcontent to set
     */
    public void setPostcontent(String postcontent) {
        this.postcontent = postcontent;
    }
    
    public boolean isEnabled(){
        try{
            System.out.println("get User "+getDatabase().getUser(getSessiondata().getUserId()).getName());
            return getSessiondata().getUserId() != -1 && getSessiondata().getUser().getType().equals(UserType.content);
        } catch(Exception ex){
            return true;
        }
    }

    /**
     * @return the userIdTest
     */
    public String getUserIdTest() {
        userIdTest = "user id: "+getSessiondata().getUserId()+", post id:"+ getSessiondata().getCurrentPostId();
        return userIdTest;
    }

    /**
     * @param userIdTest the userIdTest to set
     */
    public void setUserIdTest(String userIdTest) {
        this.userIdTest = userIdTest;
    }

    /**
     * @return the showPostButton
     */
    public boolean isShowPostButton() {
        return isEnabled() ? showPostButton : false;
    }

    /**
     * @param showPostButton the showPostButton to set
     */
    public void setShowPostButton(boolean showPostButton) {
        this.showPostButton = showPostButton;
    }

    /**
     * @return the showUpdateButton
     */
    public boolean isShowUpdateButton() {
        return isEnabled() ? showUpdateButton : false;
    }

    /**
     * @param showUpdateButton the showUpdateButton to set
     */
    public void setShowUpdateButton(boolean showUpdateButton) {
        this.showUpdateButton = showUpdateButton;
    }

    /**
     * @return the database
     */
    /*public Database getDatabase() {
        return super.getDatabase();
    }*/

    /**
     * @param database the database to set
     */
    @Override
    public void setDatabase(Database database) {
        super.setDatabase(database);
        int postid = getSessiondata().getCurrentPostId();
        System.out.println("Postid: "+postid);
                
        if(postid != -1){
            Post tmp = database.getPost(postid);
            try{
                title = tmp.getTitle();
                postcontent = tmp.getContent();
                showPostButton = false;
                showUpdateButton = true;
            } catch (NullPointerException ex){
                showPostButton = true;
                showUpdateButton = false;
            }
        }
    }
}

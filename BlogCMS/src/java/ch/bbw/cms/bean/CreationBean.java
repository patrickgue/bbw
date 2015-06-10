/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import javax.faces.bean.*;
import ch.bbw.cms.database.Database;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.helper.SessionData;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.inf.DatabaseControlInf;
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
    private SessionData session;
   
    private boolean showPostButton = true;
    private boolean showUpdateButton = false;
    
    
    public CreationBean(){
        //database = new Database();
        session = new SessionData();
        
        
        
        
    }

    public String createPost(){
        int userid = session.getUserId();
        
        if(getDatabase().createPost(title, postcontent,userid, new Date())){
            return "main.xhtml";
        } else {
            return "create.xhtml";
        }
    }
    
    public String updatePost(){
       
        if(getDatabase().updatePost(session.getCurrentPostId(), title, postcontent)){
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
            System.out.println("get User "+getDatabase().getUser(session.getUserId()).getName());
            return session.getUserId() != -1 && getDatabase().getUser(session.getUserId()).getType().equals(UserType.content);
        } catch(Exception ex){
            return true;
        }
    }

    /**
     * @return the userIdTest
     */
    public String getUserIdTest() {
        userIdTest = "user id: "+session.getUserId()+", post id:"+ session.getCurrentPostId();
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
    public Database getDatabase() {
        return getDatabase();
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(Database database) {
        this.setDatabase(database);
        int postid = session.getCurrentPostId();
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

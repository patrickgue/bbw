/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import javax.faces.bean.*;
import ch.bbw.cms.database.DatabaseControl;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.helper.SessionData;
import ch.bbw.cms.mock.DatabaseControlMock;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.inf.DatabaseControlInf;
import ch.bbw.cms.inf.Log;
import ch.bbw.cms.mock.DefaultLog;
import java.util.ArrayList;
import javax.faces.context.FacesContext;


/**
 *
 * @author patrick
 */
@ManagedBean
@RequestScoped
public class CreationBean {
    private String title;
    private String postcontent;
    private String userIdTest;
    private SessionData session;
    
    private boolean showPostButton = true;
    private boolean showUpdateButton = false;
    
    
    public CreationBean(){
        session = new SessionData();
        
        int userid = session.getUserId();
        int postid = session.getCurrentPostId();
                
        if(postid != -1){
            Post tmp = database.getPost(postid);
            title = tmp.getTitle();
            postcontent = tmp.getContent();
            showPostButton = false;
            showUpdateButton = true;
        }
        
        
    }

    private DatabaseControlInf database = new DatabaseControlMock();
    
    public String createPost(){
        int userid = session.getUserId();
        
        if(database.createPost(new Post(null, title, postcontent,userid))){
            return "main.xhtml";
        } else {
            return "create.xhtml";
        }
    }
    
    public String updatePost(){
       
        if(database.updatePost(session.getCurrentPostId(), title, postcontent)){
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
        return session.getUserId() != -1 && database.getUser(session.getUserId()).getType().equals(UserType.CONTENT);
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
}

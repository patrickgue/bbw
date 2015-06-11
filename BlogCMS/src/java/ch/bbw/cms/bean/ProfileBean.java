/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.bbw.cms.bean;

import ch.bbw.cms.database.Database;
import ch.bbw.cms.helper.SessionData;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.models.User;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 5ia13jethayalan
 */

@ManagedBean
@SessionScoped

public class ProfileBean extends AllPageBean{
   
    
    
    private String newPassword;
    private String repeatPassword;
    private ArrayList<Post> pinedPosts;
    private String search = "Search Users";
   
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
   
    public String change()
    {
        int userId = getSessiondata().getUserId();
        getDatabase().changeUserPassword(userId, newPassword);
        User user = getSessiondata().getUser();
         
        return "profile.xhtml";

    }
    
    public String showUserInfo()
    {
        User username = getSessiondata().getUser();
        return "profile.xhtml";
    }

    /**
     * @return the pinedPosts
     */
    public ArrayList<Post> getPinedPosts() {
        pinedPosts = getDatabase().getPinwall(getDatabase().getUser(getSessiondata().getUserId()));
        return pinedPosts;
    }

    /**
     * @param pinedPosts the pinedPosts to set
     */
    public void setPinedPosts(ArrayList<Post> pinedPosts) {
        this.pinedPosts = pinedPosts;
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
        
        return "profile.xhtml";
    }
    
}

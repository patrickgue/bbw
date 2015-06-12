/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.helper.SessionData;
import ch.bbw.cms.models.User;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author guenthard
 */
@ManagedBean
@SessionScoped
public class AdminBean extends AllPageBean{
    private String databaseconfig;
    
    private ArrayList<User> users;
    private SessionData session;
    private boolean render = true;
    private User toChangeUser;
    

    public AdminBean() {
        
        
        users = getDatabase().getUserList();
        
    }
  
    

    /**
     * @return the databaseconfig
     */
    public String getDatabaseconfig() {
        return databaseconfig;
    }

    /**
     * @param databaseconfig the databaseconfig to set
     */
    public void setDatabaseconfig(String databaseconfig) {
        this.databaseconfig = databaseconfig;
    }

    /**
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * @return the render
     */
    public boolean isRender() {
        return render;
    }

    /**
     * @param render the render to set
     */
    public void setRender(boolean render) {
        this.render = render;
    }
    
    public boolean isShowError(){
        return !render;
    }
    
    public void setShowError(boolean r){
        render =  !r;
    }
    
    public String editMode(){
        return "admin.xhtml";
    }

    /**
     * @return the toChangeUser
     */
    public User getToChangeUser() {
        return toChangeUser;
    }

    public String deleteUser(){
        getDatabase().deleteUser(toChangeUser.getUserId());
        users = getDatabase().getUserList();
        return "admin.xhtml";
    }
    
    /**
     * @param toChangeUser the toChangeUser to set
     */
    public void setToChangeUser(User toChangeUser) {
        UserType tmp;
        if(toChangeUser.getType().equals(UserType.content)){
            tmp = UserType.technical;
        } else if(toChangeUser.getType().equals(UserType.technical)){
            tmp = UserType.normal;
        } else {
            tmp = UserType.content;
        }
        
        getDatabase().changeUserType(toChangeUser.getUserId(), tmp);
        toChangeUser.setType(tmp);
        this.toChangeUser = toChangeUser;
    }

    
}

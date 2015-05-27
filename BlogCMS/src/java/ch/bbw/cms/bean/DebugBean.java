/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.helper.SessionData;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author guenthard
 */
@ManagedBean
@RequestScoped
public class DebugBean {
    private SessionData session = new SessionData();
    
    private String userIdString = "";
    
    public DebugBean(){
        try{
            userIdString = "User Id: "+session.getUserId();
        } catch (NullPointerException ex){
            userIdString = "User Id: <i>error - can't load</i>";
        }
    }

    /**
     * @return the userIdString
     */
    public String getUserIdString() {
        return userIdString;
    }

    /**
     * @param userIdString the userIdString to set
     */
    public void setUserIdString(String userIdString) {
        this.userIdString = userIdString;
    }
}

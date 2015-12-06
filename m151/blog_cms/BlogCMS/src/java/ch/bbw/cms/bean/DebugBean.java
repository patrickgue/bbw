/**
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * Bean used to display raw data from the application (i.e. data saved in the session)
 */
@ManagedBean
@RequestScoped
public class DebugBean extends AllPageBean{
    private String userIdString = "";
    
    public DebugBean(){
        try{
            userIdString = "User Id: "+getSessiondata().getUserId();
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

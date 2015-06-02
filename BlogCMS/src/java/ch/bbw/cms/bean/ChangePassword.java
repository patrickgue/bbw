/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.bbw.cms.bean;

import ch.bbw.cms.database.Database;
import ch.bbw.cms.helper.SessionData;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 5ia13jethayalan
 */

@ManagedBean
@SessionScoped

public class ChangePassword {
    private Database db = new Database();
    private SessionData sessiondata = new SessionData();
    
    private String newPassword;
    private String repeatPassword;

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
   
    public void change()
    {
        int userId = sessiondata.getUserId();
        db.changeUserPassword(userId, newPassword);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.database.Database;
import ch.bbw.cms.enums.UserGender;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.helper.SessionData;
import ch.bbw.cms.inf.DatabaseControlInf;
import ch.bbw.cms.models.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author guenthard
 */
@ManagedBean
@RequestScoped
public class LoginSignupBean extends AllPageBean{
    private String username;
    private String password;
    private String repassword;
    private String email;
    private String gender;
    
    
    public LoginSignupBean(){
        //database = new Database();
        sessiondata = new SessionData();
    }
            
    
    public String login(){
        
        int userid = getDatabase().checkUser(username, password);
        if(userid != -1){
            sessiondata.setUserId(userid);
            return "main.xhtml";
        } else {
            return "login.xhtml";
        }
    }
    
    public String signup(){
        if(password.equals(repassword)){
            if(getDatabase().createUser(new User(username, password, email, UserGender.valueOf(gender), UserType.normal))){
                int userid = getDatabase().getUserId(username);
                sessiondata.setUserId(userid);
                return "main.xhtml";
            } else {
                return "login.xhtml";
            }
        } else {
            return "login.xhtml";
        }
        
    }
    

    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the repassword
     */
    public String getRepassword() {
        return repassword;
    }

    /**
     * @param repassword the repassword to set
     */
    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the database
     */
    public Database getDatabase() {
        return super.getDatabase();
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(Database database) {
        super.setDatabase(database);
    }
    
    
}

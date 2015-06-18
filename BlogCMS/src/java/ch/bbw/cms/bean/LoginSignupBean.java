/**
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.database.Database;
import ch.bbw.cms.enums.UserGender;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.helper.Const;
import ch.bbw.cms.models.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * Bean used to handle the login and signup
 */
@ManagedBean
@RequestScoped
public class LoginSignupBean extends AllPageBean{
    private String username;
    private String password;
    private String repassword;
    private String email;
    private String gender;
    private String showError = "false";
    private String errorTitle = "Title";
    private String errorMessage = "Message";
    
    
    public LoginSignupBean(){
    }
            
    
    public String login(){
        
        int userid = getDatabase().checkUser(username, password);
        if(userid != -1){
            getSessiondata().setUser(getDatabase().getUser(userid));
            if(Const.LOG_DEBUG){
                try{
                    logger.debug("User is loggedin with ID: "+userid + "(id in sessionstorage: "+getSessiondata().getUser().getUserId());
                } catch(NullPointerException ex){
                    logger.error("application wasn't able to set User", ex);
                }
            }
            return "main.xhtml";
        } else {
            return "login.xhtml";
        }
    }
    
    public String closeError(){
        showError = "false";
        return "login.xhtml";
    } 
    
    public String signup(){
        if(password.equals(repassword)){
            if(getDatabase().createUser(new User(username, password, email, UserGender.valueOf(gender), UserType.normal))){
                int userid = getDatabase().getUserId(username);
                getSessiondata().setUser(getDatabase().getUser(userid));
                return "main.xhtml";
            } else {
                return "login.xhtml";
            }
        } else {
            errorMessage = "password don't match";
            errorTitle = "Error!";
            showError = "true";
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

    /**
     * @return the showError
     */
    public String getShowError() {
        return showError;
    }

    /**
     * @param showError the showError to set
     */
    public void setShowError(String showError) {
        this.showError = showError;
    }

    /**
     * @return the errorTitle
     */
    public String getErrorTitle() {
        return errorTitle;
    }

    /**
     * @param errorTitle the errorTitle to set
     */
    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
}

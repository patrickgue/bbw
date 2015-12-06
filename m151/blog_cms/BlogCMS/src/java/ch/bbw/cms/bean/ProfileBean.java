/**
 * @author: 5ia13jethayalan
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */

package ch.bbw.cms.bean;

import ch.bbw.cms.enums.UserGender;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.models.User;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * Bean to display profil information: user data and pined posts 
 */
@ManagedBean
@SessionScoped
public class ProfileBean extends AllPageBean{
   
    
    
    private String newPassword;
    private String repeatPassword;
    private ArrayList<Post> pinedPosts;
    private ArrayList<User> searchedUsers = new ArrayList();
    private boolean showSearchList = true;
    private String search = "Search Users";
    private Post currentSelectedPost;
    private User currentUser;
    private User logedInUser;
    private String showError = "false";
    private String errorTitle = "Error";
    private String errorMessage = "Lorem Ipsum";
    private String errorCloseLocation = "profile.xhtml";
    
    
    public ProfileBean(){
        try{
            currentUser = getSessiondata().getUser();
            logedInUser = currentUser;
        } catch (NullPointerException ex){
            errorCloseLocation = "login.xhtml";
            showError = "true";
            errorTitle = "Error!";
            errorMessage = "session expired! Login again";
        }
        
    }
   
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
   
    public String change(){
        if(newPassword.equals(repeatPassword)){
            getDatabase().changeUserPassword(getSessiondata().getUserId(), newPassword);
            User user = getSessiondata().getUser();
            user.setPassword(newPassword);
        } else {
            errorTitle = "Error!";
            errorMessage = "passwords don't match";
            showError = "true";
            errorCloseLocation = "profile.xhtml";
        }
        
         
        return "profile.xhtml";

    }
    
    public String closeError(){
        showError = "false";
        return errorCloseLocation;
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
        try{
            return getDatabase().getPinwall(currentUser);
        } catch(NullPointerException ex){
            return new ArrayList<Post>();
        }
        
    }

    /**
     * @param pinedPosts the pinedPosts to set
     */
    public void setPinedPosts(ArrayList<Post> pinedPosts) {
        this.pinedPosts = pinedPosts;
    }
    
    public String deletePinedPost(){
        getDatabase().deletePostFromPinwall(getDatabase().getPinId(getCurrentUser().getUserId(), getCurrentSelectedPost().getPostId()));
        return "profile.xhtml";
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
        ArrayList<User> allUsers = getDatabase().getUserList();
        searchedUsers.clear();
        for(User usr : allUsers){
            if(usr.getName().toLowerCase().contains(search.toLowerCase()) ||
                    usr.getEmail().toLowerCase().contains(search.toLowerCase()) ){
                searchedUsers.add(usr);
            }
        }
        return "profile.xhtml";
    }

    /**
     * @return the currentSelectedPost
     */
    public Post getCurrentSelectedPost() {
        return currentSelectedPost;
    }

    /**
     * @param currentSelectedPost the currentSelectedPost to set
     */
    public void setCurrentSelectedPost(Post currentSelectedPost) {
        this.currentSelectedPost = currentSelectedPost;
    }

    /**
     * @return the searchedUsers
     */
    public ArrayList<User> getSearchedUsers() {
        return searchedUsers;
    }

    /**
     * @param searchedUsers the searchedUsers to set
     */
    public void setSearchedUsers(ArrayList<User> searchedUsers) {
        this.searchedUsers = searchedUsers;
    }

    /**
     * @return the showSearchList
     */
    public boolean isShowSearchList() {
        return showSearchList;
    }

    /**
     * @param showSearchList the showSearchList to set
     */
    public void setShowSearchList(boolean showSearchList) {
        this.showSearchList = showSearchList;
    }

    /**
     * @return the logedInUser
     */
    public User getLogedInUser() {
        return logedInUser;
    }

    /**
     * @param logedInUser the logedInUser to set
     */
    public void setLogedInUser(User logedInUser) {
        this.logedInUser = logedInUser;
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

    /**
     * @return the currentUser
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author 5ia13jethayalan
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
    
    public ProfileBean(){
        currentUser = getDatabase().getUser(getSessiondata().getUserId());
        searchedUsers.add(new User("testuser", "password", "email", UserGender.other, UserType.normal));
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
    
    public String deletePinedPost(){
        getDatabase().deletePostFromPinwall(getDatabase().getPinId(currentUser.getUserId(), getCurrentSelectedPost().getPostId()));
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
    
}

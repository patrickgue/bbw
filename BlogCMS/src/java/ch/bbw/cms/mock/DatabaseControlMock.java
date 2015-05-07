/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.mock;

import ch.bbw.cms.enums.UserGender;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.models.User;

import java.util.ArrayList;

/**
 *
 * @author guenthard
 */
public class DatabaseControlMock {
    ArrayList<User> users = new ArrayList<User>();
	
    public DatabaseControlMock(){
        users.add(new User(1, "dummy1", "asdf", "dummy@dummy1", UserGender.FEMALE, UserType.NORMAL));
        users.add(new User(2, "dummy1", "asdf", "dummy@dummy2", UserGender.FEMALE, UserType.NORMAL));
        users.add(new User(3, "dummy1", "asdf", "dummy@dummy3", UserGender.FEMALE, UserType.NORMAL));
        users.add(new User(4, "dummy1", "asdf", "dummy@dummy4", UserGender.FEMALE, UserType.NORMAL));
    }
    
    public ArrayList<Post> getPosts(User user){
	return getPostList(user.getUserId());
    }
    
    public ArrayList<Post> getPosts(){
	return getPostList(null);
    }
    
    public ArrayList<Post> getPosts(int userId){
        return getPostList(userId);
    }

    public ArrayList<Post> getPostList(Integer userId){
	ArrayList<Post> posts = new ArrayList<Post>();
	
        posts.add(new Post(0, "blabla", "title of blabla 1"));
        posts.add(new Post(1, "hihi", "asdf"));
        
	return posts;
    }
    
    public ArrayList<User> getUserList(){
        
	return users;
    }
    
    public boolean createUser(){
        users.add(null);
        return true;
    }
    
    
}

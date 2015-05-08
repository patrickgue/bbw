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
import ch.bbw.cms.inf.DatabaseControlInf;


import java.util.ArrayList;

/**
 *
 * @author guenthard
 */
public class DatabaseControlMock implements DatabaseControlInf{
    ArrayList<User> users = new ArrayList<User>();
	
    public DatabaseControlMock(){
        users.add(new User(1, "dummy1", "asdf", "dummy@dummy1", UserGender.FEMALE, UserType.NORMAL));
        users.add(new User(2, "dummy1", "asdf", "dummy@dummy2", UserGender.FEMALE, UserType.NORMAL));
        users.add(new User(3, "dummy1", "asdf", "dummy@dummy3", UserGender.FEMALE, UserType.NORMAL));
        users.add(new User(4, "dummy1", "asdf", "dummy@dummy4", UserGender.FEMALE, UserType.NORMAL));
    }
    
    @Override
    public ArrayList<Post> getPosts(User user){
	return getPostList(user.getUserId());
    }
    
    @Override
    public ArrayList<Post> getPosts(){
	return getPostList(null);
    }
    
    @Override
    public ArrayList<Post> getPosts(int userId){
        return getPostList(userId);
    }

    @Override
    public ArrayList<Post> getPostList(Integer userId){
	ArrayList<Post> posts = new ArrayList<Post>();
	
        posts.add(new Post(0, "title of blabla 1", "blabla"));
        posts.add(new Post(1, "hihi", "asdf"));
        
	return posts;
    }
    
    @Override
    public ArrayList<User> getUserList(){
        
	return users;
    }
    
    @Override
    public boolean createUser(){
        users.add(null);
        return true;
    }

    @Override
    public ArrayList<Post> getPosts(String searchterm) {
        ArrayList<Post> posts = new ArrayList<Post>();
	
        posts.add(new Post(0, "title of omg 1", "omg"));
        posts.add(new Post(1, "abc", "some text Lorem Ipsum hello world Telephone Banana Java Abstract etc."));
        
	return posts;
    }
    
    @Override
    public boolean checkUser(String username, String password){
        
        return true;
    }
    
    
}

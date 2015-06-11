/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.inf;

import ch.bbw.cms.enums.UserGender;
import ch.bbw.cms.enums.UserType;

import ch.bbw.cms.models.*;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author guenthard
 */
public interface DatabaseControlInf {
    public ArrayList<Post> getPosts(User user);
    public ArrayList<Post> getPosts();
    public ArrayList<Post> getPosts(int userId);
    public ArrayList<Post> getPosts(String searchterm);
    public Post getPost(int id);
    public ArrayList<Post> getPostList(Integer userId);
    public ArrayList<User> getUserList();
    public int checkUser(String username, String password);
    public boolean createUser(String username, String password, String email, UserGender gender, UserType type);
    public boolean createUser(User user);
    public boolean createPost(Post post);
    public boolean createPost(String title, String content, int userid, Date date);
    public boolean updatePost(int postid, String title, String content);
    public boolean changeUserPassword(int userId, String newPw);
    public boolean changeUserBio(int userId, String bio);
    public boolean changeUserType(int userId, UserType type);
    public int getUserId(String nameOrEmail);
    public User getUser(int id);
    public ArrayList<Post> getPinwall(User user);
    public int getPinId(int userid, int post);
    public boolean addPostToPinwall(User user, Post post);
    public boolean addPostToPinwall(int user, int post);
    public boolean deletePostFromPinwall(int pinid);
    public ArrayList<Comment> getComments(int postid);
    public boolean addComment(Comment comment);
}

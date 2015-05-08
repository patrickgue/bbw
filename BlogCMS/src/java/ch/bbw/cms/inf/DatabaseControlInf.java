/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.inf;

import ch.bbw.cms.models.*;

import java.util.ArrayList;

/**
 *
 * @author guenthard
 */
public interface DatabaseControlInf {
    public ArrayList<Post> getPosts(User user);
    public ArrayList<Post> getPosts();
    public ArrayList<Post> getPosts(int userId);
    public ArrayList<Post> getPostList(Integer userId);
    public ArrayList<User> getUserList();
    public boolean createUser();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.bbw.cms.database;

import ch.bbw.cms.enums.*;
import java.util.*;
import java.sql.*;

import ch.bbw.cms.models.*;
import ch.bbw.cms.inf.DatabaseControlInf;


/**
 * This Class is used to connect to the database in the internet. it provides methods which can be called from the application.

 * <b>This class mustn't be directly included into the viewer files (i.e. xhtml files) because of security reasons</b>
 * @author 5ia13paguenthard
 */
public class DatabaseControl implements DatabaseControlInf{
    private Connection conn;
    
    
    public DatabaseControl(){
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection("jdbc:mysql://db51.netzone.ch/rogerguenthar","rogerguenthar","cms001");
            
        } catch (ClassNotFoundException ex) {  
            ex.printStackTrace();
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
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
	String query;
        if(userId == null){
            query = "SELECT * FROM cms_post";
        } else {
            query = "SELECT * FROM cms_post WHERE post_user_id = "+userId;
        }
        try {
	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {
		int id = rs.getInt("post_id");
		String content = rs.getString("post_content");
		String title = rs.getString("post_title"); 
		posts.add(new Post(id, title, content));
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	return posts;
    }
    
    @Override
    public ArrayList<User> getUserList(){
        ArrayList<User> users = new ArrayList<User>();
	String query = "SELECT * FROM cms_user";
        try {
	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String password = rs.getString("user_password");
                String email = rs.getString("user_email");
                UserType type = UserType.getUserTypeFromString(rs.getString("user_type"));
                String bio = rs.getString("user_bio");
                int age = rs.getInt("user_age");
                UserGender gender = UserGender.getUserTypeFromString(rs.getString("user_gender"));
		users.add(new User(id, name, password, email, gender, type, bio, age));
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	return users;
    }
    
    @Override
    public boolean createUser(String username, String password, String email, UserGender gender, UserType type){
        String query = "INSERT INTO cms_user (user_name, user_password, user_email, user_gender, user_type)  values("
                + " '"+username+"'"
                + ",'"+password+"'"
                + ",'"+email+"'"
                + ",'"+gender.getGenderName()+"'"
                + ",'"+type.getType()+"')";
        return execute(query);
    }
    
    
    public boolean createUser(User user){
        return createUser(user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getGender(),
                user.getType());
    }
    
    // FIXME: remove on release
    public static void main(String[] args){
        DatabaseControl ctrl = new DatabaseControl();
        ArrayList<Post> test = ctrl.getPosts(0);
        System.out.println(test.get(0).getContent());
    }

    @Override
    public ArrayList<Post> getPosts(String searchterm) {
        ArrayList<Post> tmpPosts = getPosts();
        ArrayList<Post> returnPosts = new ArrayList<Post>();
        
        for(Post p : tmpPosts){
            if(p.getContent().contains(searchterm) ||
                    p.getTitle().contains(searchterm)){
                returnPosts.add(p);
            }
        }
        
        return returnPosts;
    }

   
    @Override
    public int checkUser(String username, String password){
        ArrayList<User> users = getUserList();
        
        for(User tmp : users){
            if(tmp.getEmail().equals(username) || tmp.getName().equals(username)){
                if(tmp.getPassword().equals(password)){
                    return tmp.getUserId();
                }
            }
        }
        return -1;
    }
    
    @Override
    public boolean createPost(int userid, String title, String content){
        String query = "INSERT INTO cms_post (post_user_id, post_title, post_content, post_likes)  values("
                + ""+userid
                + ",'"+title+"'"
                + ",'"+content+"'"
                + ", 0)";
        
        return execute(query);
    }
    
    private boolean execute(String query){
        try {
	    Statement st = conn.createStatement();
	    st.executeUpdate(query);
	} catch (SQLException ex) {
	    ex.printStackTrace();
            return false;
	}
        return true;
    }

    @Override
    public boolean createPost(Post post) {
        return createPost(post.getUserId(), post.getTitle(), post.getContent());
    }

    @Override
    public boolean changeUserBio(int userId, String bio) {
        return true;
    }

    @Override
    public boolean changeUserPassword(int userId, String newPw) {
        return true;
    }
    
    
}

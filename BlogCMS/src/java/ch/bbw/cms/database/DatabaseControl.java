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


/**
 * This Class is used to connect to the database in the internet. it provides methods which can be called from the application.

 * <b>This class mustn't be directly included into the viewer files (i.e. xhtml files) because of security reasons</b>
 * @author 5ia13paguenthard
 */
public class DatabaseControl {
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
		posts.add(new Post(id, content, title));
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	return posts;
    }
    
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
    
    public boolean createPost(){
        String query = "INSERT INTO cms_user (user_name, user_password, user_email)";
        try {
	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    if(rs == null){
                return false;
            }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
        return true;
    }
    
    
    // FIXME: remove on release
    public static void main(String[] args){
        DatabaseControl ctrl = new DatabaseControl();
        ArrayList<Post> test = ctrl.getPosts(0);
        System.out.println(test.get(0).getContent());
    }

   
    
}

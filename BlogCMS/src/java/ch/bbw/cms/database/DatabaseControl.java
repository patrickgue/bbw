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
            
            //conn = DriverManager.getConnection("jdbc:mysql://db51.netzone.ch/rogerguenthar","rogerguenthar","cms001");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/cms","root","");
            
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
                int userIdGet = rs.getInt("post_user_id");
		String content = rs.getString("post_content");
		String title = rs.getString("post_title"); 
		posts.add(new Post(id, title, content, userIdGet));
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
                UserType type = UserType.valueOf(rs.getString("user_type"));
                String bio = rs.getString("user_bio");
                int age = rs.getInt("user_age");
                UserGender gender = UserGender.valueOf(rs.getString("user_gender"));
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
            if(p.getContent().toLowerCase().contains(searchterm.toLowerCase()) ||
                    p.getTitle().toLowerCase().contains(searchterm.toLowerCase())){
                returnPosts.add(p);
            }
        }
        
        return returnPosts;
    }

    @Override
    public Post getPost(int postId){
        String query = "SELECT * FROM cms_post WHERE post_id = "+postId;
        System.out.println(query);
        try {
	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    
            if(rs.next()){
            
                int    post = rs.getInt("post_id");
                String titl = rs.getString("post_title");
                String cont = rs.getString("post_content");
                int    user = rs.getInt("post_user_id");

                return new Post(post, titl, cont, user);
            }

	} catch (SQLException | NullPointerException ex) {
	    //ex.printStackTrace();
	}
	return null;
    }
   
    @Override
    public int checkUser(String username, String password){
        ArrayList<User> users = getUserList();
        
        for(User tmp : users){
            if(tmp.getEmail().equals(username) || tmp.getName().equals(username)){
                System.out.printf("%-16s", username);
                if(tmp.getPassword().equals(password)){
                    System.out.println("password matches! (userid: "+tmp.getUserId()+")");
                    return tmp.getUserId();
                } else {
                    System.out.println();
                }
            }
        }
        return -1;
    }
    
    @Override
    public User getUser(int userId){
        String query = "SELECT * FROM cms_user WHERE user_id = "+userId;
        System.out.println("Query: "+query);
        try {
	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    
            if(rs.next()){
            //int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String passw = rs.getString("user_password");
                String email = rs.getString("user_email");
                String gender = rs.getString("user_gender");
                String type = rs.getString("user_type");
                
                return new User(userId, name, passw, email, UserGender.valueOf(gender), UserType.valueOf(type));
            }
            

	} catch (NullPointerException ex) {
	    ex.printStackTrace();
	} catch (SQLException ex1){
            System.err.println("Sql Error");
            ex1.printStackTrace();
        }
	return null;
    }
    
    @Override
    public boolean createPost(String title, String content, int userId){
        String query = "INSERT INTO cms_post (post_title, post_content, post_user_id, post_likes)  values("
                + "'"+title+"'"
                + ",'"+content+"'"
                + ", "+userId+""
                + ", 0)";
        
        return execute(query);
    }
    
    @Override
    public boolean updatePost(int postId, String title, String content){
        String query = "UPDATE cms_post SET "
                + "post_title='"+title+"'"
                + ", post_content='"+content+"'"
                + " WHERE post_id = "+postId;
        System.out.println("Update post query: "+query);
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
        return createPost(post.getTitle(), post.getContent(), post.getUserId());
    }

    @Override
    public boolean changeUserBio(int userId, String bio) {
        String query = "UPDATE cms_user SET user_bio =\""+bio+"\" WHERE user_id = " + userId;
        return execute(query);
    }

    @Override
    public boolean changeUserPassword(int userId, String newPw) {
        String query = "UPDATE cms_user SET user_password =\""+newPw+"\" WHERE user_id = " + userId;
        return execute(query);
    }
    
    @Override
    public boolean changeUserType(int userId, UserType type){
        String query = "UPDATE cms_user SET user_type =\""+type.getType()+"\" WHERE user_id = " + userId;
        return execute(query);
    }

    @Override
    public int getUserId(String nameOrEmail) {
	String query = "SELECT * FROM cms_user WHERE user_name = \""+nameOrEmail+"\" OR user_email = \""+nameOrEmail+"\"";
        
        try {
	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    
            int id = rs.getInt("user_id");
	    if(id != 0 || id != -1){
                return id;
            }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	return -1;
    }
    
    
    
}

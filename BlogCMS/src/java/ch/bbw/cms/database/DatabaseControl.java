/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.bbw.cms.database;



import ch.bbw.cms.enums.*;
import ch.bbw.cms.helper.SessionData;
import ch.bbw.cms.inf.DatabaseControlInf;
import ch.bbw.cms.models.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



/**
 * This Class is used to connect to the database in the internet. it provides methods which can be called from the application.

 * <b>This class mustn't be directly included into the viewer files (i.e. xhtml files) because of security reasons</b>
 * @author 5ia13paguenthard
 */

public class DatabaseControl implements DatabaseControlInf{
    private Connection conn;
    private SessionData session;
    
    
    public DatabaseControl(){
        session = new SessionData();
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = connect();
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/cms","root","");
            
        } catch (ClassNotFoundException ex) {
        } 
    }
    
    private Connection connect(){
        if(conn !=  null){
            return conn;
        }
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/cms","root","");
            //return DriverManager.getConnection("jdbc:mysql://db51.netzone.ch/rogerguenthar","rogerguenthar","cms001");
        } catch(SQLException ex){
            ex.printStackTrace();
            return null;
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
	ArrayList<Post> posts = new ArrayList<>();
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
                java.sql.Date result =  rs.getDate("post_date");  
		posts.add(new Post(id, title, content, userIdGet, result));
	    }
            
            st.close();
	} catch (SQLException ex) {
            System.out.println("##############");
            ex.printStackTrace();
	}
	return posts;
    }
    
    @Override
    public ArrayList<User> getUserList(){
        ArrayList<User> users = new ArrayList<>();
	String query = "SELECT * FROM cms_user";
        try {
	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String password = rs.getString("user_password");
                String email = rs.getString("user_email");
                UserType type = UserType.valueOf(rs.getString("user_type").toUpperCase());
                String bio = rs.getString("user_bio");
                int age = rs.getInt("user_age");
                UserGender gender = UserGender.valueOf(rs.getString("user_gender").toUpperCase());
		users.add(new User(id, name, password, email, gender, type, bio, age));
	    }
           
            st.close();
	} catch (SQLException ex) {
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
    
    
    @Override
    public boolean createUser(User user){
        return createUser(user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getGender(),
                user.getType());
    }
    
    @Override
    public ArrayList<Post> getPosts(String searchterm) {
        ArrayList<Post> tmpPosts = getPosts();
        ArrayList<Post> returnPosts = new ArrayList<>();
        
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
        try {
	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    
            if(rs.next()){
            
                int    post = rs.getInt("post_id");
                String titl = rs.getString("post_title");
                String cont = rs.getString("post_content");
                int    user = rs.getInt("post_user_id");
                Date result =  rs.getDate("post_date");  
                return new Post(post, titl, cont, user, result);
            }
            
            st.close();

	} catch (SQLException | NullPointerException ex) {
	}
	return null;
    }
   
    @Override
    public int checkUser(String username, String password){
        ArrayList<User> users = getUserList();
        
        for(User tmp : users){
            if(tmp.getEmail().equals(username) || tmp.getName().equals(username)){
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
        User fromSession = session.getUser();
        
        if(fromSession != null){
            if(fromSession.getUserId() == userId){
                return fromSession;
            }
        }
        
        if(conn == null){
            conn = connect();
        }
        String query = "SELECT * FROM cms_user WHERE user_id = "+userId;
        try {
	    Statement st = conn.createStatement();
            
	    ResultSet rs = st.executeQuery(query);
	    
            if(rs.next()){
                String name = rs.getString("user_name");
                String passw = rs.getString("user_password");
                String email = rs.getString("user_email");
                String gender = rs.getString("user_gender");
                String type = rs.getString("user_type");

                
                return new User(userId, name, passw, email, UserGender.valueOf(gender.toUpperCase()), UserType.valueOf(type.toUpperCase()));
            }
            st.close();

	} catch (NullPointerException ex) {
	} catch (SQLException ex1){
            System.err.println("Sql Error");
        }
	return null;
    }
    
    @Override
    public boolean createPost(String title, String content, int userId, Date date){
        
        
        String query = "INSERT INTO cms_post (post_title, post_content, post_user_id, post_likes, post_date)  values("
                + "'"+title+"'"
                + ",'"+content+"'"
                + ", "+userId+""
                + ", 0"
                + ", '"+new java.sql.Date(date.getTime()).toString()+"' )";
        System.out.println(query);
        return execute(query);
    }
    
    @Override
    public boolean updatePost(int postId, String title, String content){
        String query = "UPDATE cms_post SET "
                + "post_title='"+title+"'"
                + ", post_content='"+content+"'"
                + " WHERE post_id = "+postId;
        return execute(query);
    }
    
    private boolean execute(String query){
        if(conn == null){
            conn = connect();
        }
        
        try {
	    Statement st;
            
            st = conn.createStatement();
	    st.executeUpdate(query);
            st.close();
	} catch (SQLException ex) {
            return false;
	} catch(NullPointerException ex1){
            return false;
        }
        return true;
    }

    @Override
    public boolean createPost(Post post) {
        return createPost(post.getTitle(), post.getContent(), post.getUserId(), post.getDate());
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
            st.cancel();
	} catch (SQLException ex) {
	}
	return -1;
    }

    @Override
    public ArrayList<Post> getPinwall(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addPostToPinwall(User user, Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePostFromPinwall(User user, Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.bbw.cms.database;

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
	return getPosts(user.getUserId());
    }

    public ArrayList<Post> getPosts(int userId){
	ArrayList<Post> posts = new ArrayList<Post>();
	String query = "SELECT * FROM cms_post WHERE post_user_id = "+userId;
        try {
	    Statement st = conn.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next()) {
		int id = userId;
		String content = rs.getString("post_content");
		String title = "Fake Title";//rs.getString("post_title"); // TODO implement title in database
		posts.add(new Post(id, content, title));
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	return posts;
    }
    
    // FIXME: remove on release
    public static void main(String[] args){
        DatabaseControl ctrl = new DatabaseControl();
        ArrayList<Post> test = ctrl.getPosts(0);
        System.out.println(test.get(0).getContent());
    }
    
    
}

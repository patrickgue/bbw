package ch.bbw.cms.database;

import ch.bbw.cms.enums.*;
import ch.bbw.cms.inf.DatabaseControlInf;
import ch.bbw.cms.mock.DatabaseControlMock;
import ch.bbw.cms.models.Comment;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.models.User;
import java.util.*;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 * This Class is used as a wrapper for the database class. The main purpose is that it has to be easy to 
 * switch between the mock and the real database.
 * @author 5ia13paguenthard
 */
@ManagedBean(name="database")
@SessionScoped 
public class Database implements DatabaseControlInf{
    private DatabaseControlInf db;

    public Database(){
        db = new DatabaseControlMock();//HibernateDatabase();
    }
    
    @Override
    public ArrayList<Post> getPosts(User user){
	return db.getPosts(user);
    }
    
    @Override
    public ArrayList<Post> getPosts(){
	return db.getPosts();
    }
    
    @Override
    public ArrayList<Post> getPosts(int userId){
        return db.getPosts(userId);
    }

    @Override
    public ArrayList<Post> getPostList(Integer userId){
	return db.getPostList(userId);
    }
    
    @Override
    public ArrayList<User> getUserList(){
        return db.getUserList();
    }
    

    @Override
    public ArrayList<Post> getPosts(String searchterm) {
	return db.getPosts(searchterm);
    }
    
    @Override
    public Post getPost(int postId){
	return db.getPost(postId);
    }
    
    @Override
    public int checkUser(String username, String password){
        return db.checkUser(username, password);
    }

    @Override
    public boolean createUser(String username, String password, String email, UserGender gender, UserType type) {
        return db.createUser(username, password, email, gender, type);
    }
    
    @Override
    public boolean createUser(User user){
        return db.createUser(user);
    }
    
    @Override
    public boolean createPost(Post post){
        return db.createPost(post);
    }
    
    @Override
    public boolean createPost(String title, String content, int userId, Date date){
        return db.createPost(title, content, userId, date);
    }

    @Override
    public boolean changeUserBio(int userId, String bio) {
        return db.changeUserBio(userId, bio);
    }

    @Override
    public boolean changeUserPassword(int userId, String newPw) {
        return db.changeUserPassword(userId, newPw);
    }

    @Override
    public int getUserId(String nameOrEmail) {
	return db.getUserId(nameOrEmail);
    }

    @Override
    public boolean updatePost(int postId, String title, String content) {
        return db.updatePost(postId, title, content);
    }
    
    @Override
    public User getUser(int id){
	return db.getUser(id);
    }

    @Override
    public boolean changeUserType(int userId, UserType type){
        return db.changeUserType(userId, type);
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

    @Override
    public ArrayList<Comment> getComments(int postid) {
        return db.getComments(postid);
    }

    @Override
    public boolean addComment(Comment comment) {
        return db.addComment(comment);
    }
}

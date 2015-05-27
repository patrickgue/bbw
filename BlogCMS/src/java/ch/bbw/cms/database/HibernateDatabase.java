/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.database;

import ch.bbw.cms.database.hibernate.HibernateUtil;
import ch.bbw.cms.database.hibernate.cms_post;
import ch.bbw.cms.database.hibernate.cms_user;
import ch.bbw.cms.enums.UserGender;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.inf.DatabaseControlInf;
import ch.bbw.cms.models.Comment;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.models.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author guenthard
 */
public class HibernateDatabase implements DatabaseControlInf{
    private SessionFactory factory;
    private HibernateUtil util;

    public HibernateDatabase() {
        util = new HibernateUtil();
        factory = util.getSessionFactory();
    }
    
    

    @Override
    public ArrayList<Post> getPosts(User user) {
        return getPosts(user.getUserId());
    }

    @Override
    public ArrayList<Post> getPosts(){
	return getPostList(null);
    }

    @Override
    public ArrayList<Post> getPosts(int userId) {
        return getPostList(userId);
    }

    @Override
    public ArrayList<Post> getPosts(String searchterm) {
        return getPostListBase(searchterm);
    }

    @Override
    public Post getPost(int id) {
        for(Post pst: getPostListBase(null)){
            if(pst.getPostId() == id){
                return pst;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Post> getPostList(Integer userId) {
        return getPostListBase((Integer)userId);
    }
    
    public ArrayList<Post> getPostListBase(Object userIdOrSearchTerm) {
        ArrayList<Post> posts = new ArrayList<Post>();
	String query;
        if(userIdOrSearchTerm == null){
            query = "from cms_post";
        } else {
            if(userIdOrSearchTerm instanceof String){
                query = "from cms_post where post_content='"+(String)userIdOrSearchTerm +"'"+
                        "or post_title='"+(String)userIdOrSearchTerm+"'";
            } else {
                query = "from cms_post where post_user_id = "+(Integer)userIdOrSearchTerm;
            }
            
        }
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            List lposts = session.createQuery(query).list(); 
            for (Iterator iterator = 
                              lposts.iterator(); iterator.hasNext();){
                cms_post post = (cms_post) iterator.next(); 
                
                posts.add(new Post(
                        post.getId(), 
                        post.getTitle(), 
                        post.getContent(), 
                        post.getUserId(), 
                        post.getPost_date()));

            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
            
        }finally {
            session.close(); 
        }
        
        return posts;
    }

    @Override
    public ArrayList<User> getUserList() {
        ArrayList<User> users = new ArrayList<>();
	String query = "from cms_user";
        Session session = factory.openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            List lusers = session.createQuery(query).list(); 
            for (Iterator iterator = 
                              lusers.iterator(); iterator.hasNext();){
                cms_user user = (cms_user) iterator.next(); 
                
                users.add(new User(
                        user.getId(), 
                        user.getName(), 
                        user.getPassword(), 
                        user.getEmail(), 
                        user.getGender(), 
                        user.getType()));
                  

            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
            
        }finally {
            session.close(); 
        }
        
        return users;
    }

    @Override
    public int checkUser(String username, String password) {
        for(User tmp : getUserList()){
            if(tmp.getName().equals(username) || tmp.getEmail().equals(username)){
                if(tmp.getPassword().equals(password)){
                    return tmp.getUserId();
                }
            }
        }
        return -1;
    }

    @Override
    public boolean createUser(String username, String password, String email, UserGender gender, UserType type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createPost(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createPost(String title, String content, int userid, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updatePost(int postid, String title, String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changeUserPassword(int userId, String newPw) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changeUserBio(int userId, String bio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changeUserType(int userId, UserType type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getUserId(String nameOrEmail) {
        for(User usr : getUserList()){
            if(usr.getName().equals(nameOrEmail) || usr.getEmail().equals(nameOrEmail)){
                return usr.getUserId();
            }
        }
        return -1;
    }

    @Override
    public User getUser(int id) {
        for(User usr : getUserList()){
            if(usr.getUserId() == id){
                return usr;
            }
        }
        return null;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addComment(Comment comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

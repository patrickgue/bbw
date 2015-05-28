/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.database;

import ch.bbw.cms.database.hibernate.HibernateUtil;
import ch.bbw.cms.database.hibernate.cms_comment;
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
    
    private Session initSession(){
        Session session;
        try{
            session = factory.getCurrentSession();
        } catch(HibernateException ex){
            session = factory.openSession();
        }
        return session;
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
                query = "from cms_post where post_content LIKE '%"+(String)userIdOrSearchTerm +"%'"+
                        "or post_title LIKE '%"+(String)userIdOrSearchTerm+"%'";
            } else {
                query = "from cms_post where post_user_id = "+(Integer)userIdOrSearchTerm;
            }
            
        }
        
        Session session = initSession();
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
        Session session = initSession();
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
        return execute("INSERT INTO cms_user (user_name, user_password, user_email, user_gender, user_type)  values("
                + " '"+username+"'"
                + ",'"+password+"'"
                + ",'"+email+"'"
                + ",'"+gender.getGenderName()+"'"
                + ",'"+type.getType()+"')");
        
    }

    @Override
    public boolean createUser(User user) {
        return createUser(user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getGender(),
                user.getType());
    }

    @Override
    public boolean createPost(Post post) {
        return createPost(post.getTitle(), post.getContent(), post.getUserId(), post.getDate());
    }

    @Override
    public boolean createPost(String title, String content, int userid, Date date) {
        return execute("INSERT INTO cms_post (post_title, post_content, post_user_id, post_likes, post_date)  values("
                + "'"+title+"'"
                + ",'"+content+"'"
                + ", "+userid+""
                + ", 0"
                + ", '"+new java.sql.Date(date.getTime()).toString()+"' )");
    }

    @Override
    public boolean updatePost(int postid, String title, String content) {
        return execute("UPDATE cms_post SET "
                + "post_title='"+title+"'"
                + ", post_content='"+content+"'"
                + " WHERE post_id = "+postid);
    }

    @Override
    public boolean changeUserPassword(int userId, String newPw) {
        return execute("UPDATE cms_user SET user_password =\""+newPw+"\" WHERE user_id = " + userId);
    }

    @Override
    public boolean changeUserBio(int userId, String bio) {
        return execute("UPDATE cms_user SET user_bio =\""+bio+"\" WHERE user_id = " + userId);
    }

    @Override
    public boolean changeUserType(int userId, UserType type) {
        return execute("UPDATE cms_user SET user_type =\""+type.getType()+"\" WHERE user_id = " + userId);
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
        ArrayList<Comment> comments = new ArrayList<>();
	String query = "from cms_comment where comment_post_id="+postid;
        Session session = initSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            List lcomments = session.createQuery(query).list(); 
            for (Iterator iterator = 
                              lcomments.iterator(); iterator.hasNext();){
                cms_comment comment = (cms_comment) iterator.next(); 
                
                comments.add(new Comment(comment.getId(), comment.getContent(), comment.getPostId(), comment.getUserId(), comment.getDate()));
                        
                  

            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
            
        }finally {
            session.close(); 
        }
        
        return comments;
    }

    @Override
    public boolean addComment(Comment comment) {
        return execute("INSERT INTO cms_post (comment_user_id, comment_content, comment_post_id, comment_date)  values("
                + ""+comment.getUserId()+""
                + ", '"+comment.getContent()+"'"
                + ", "+comment.getPostId()+""
                + ", '"+comment.getDate().toString()+"' )");
    }
    
    private boolean execute(String mysqlQuery){
        boolean worked = true;
        Session session = initSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            session.createSQLQuery(mysqlQuery);
            
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            worked = false;
            e.printStackTrace(); 
            
        }finally {
            session.close(); 
        }
        return worked;
    }
}

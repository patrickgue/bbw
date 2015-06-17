/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.database;

import ch.bbw.cms.database.hibernate.HibernateUtil;
import ch.bbw.cms.database.hibernate.cms_comment;
import ch.bbw.cms.database.hibernate.cms_pinwall;
import ch.bbw.cms.database.hibernate.cms_post;
import ch.bbw.cms.database.hibernate.cms_user;
import ch.bbw.cms.enums.UserGender;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.helper.Analyzer;
import ch.bbw.cms.helper.Const;
import ch.bbw.cms.helper.SessionData;
import ch.bbw.cms.inf.DatabaseControlInf;
import ch.bbw.cms.inf.Log;
import ch.bbw.cms.mock.DefaultLog;
import ch.bbw.cms.models.Comment;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.models.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
    private Log logger = new DefaultLog();
    private Analyzer analyze;
    private SessionData sessiondata;
    
    /**
     * Cache Posts, Pinwall and User lists. The lists get refreshed after {@link:#Const.REFRESH_TIME Const.REFRESH_TIME}
     */
    private ArrayList<Post> postCache;
    private Date lastCachedPosts = new Date();
    private ArrayList<User> userCache;
    private Date lastCachedUsers = new Date();
    private HashMap<Integer, ArrayList<Post>> pinwallCache;
    private Date lastCachedPinwall = new Date();
    private HashMap<String, Integer> pinwallIdCache;
    private Date lastPinwallIdCache = new Date();
    private boolean loadPinsFromDb = false;
    private HashMap<Integer, ArrayList<Comment>> commentCache = new HashMap<>();
    private Date lastCacheComment = new Date();
    
    public HibernateDatabase() {
        sessiondata = new SessionData();
        if((util = sessiondata.getDBUtil()) == null){
            util = new HibernateUtil();
            sessiondata.setDBUtil(util);
        }
        factory = util.getSessionFactory();
        analyze = new Analyzer("hibernateDb");
    }
    
    private Session initSession(){
        
        try{
            return factory.getCurrentSession();
        } catch(HibernateException ex){
            return factory.openSession();
        }
    }

    
    
    @Override
    public ArrayList<Post> getPosts(User user) {
        analyze.count("getPostsFromUserClass");
        return getPosts(user.getUserId());
    }

    @Override
    public ArrayList<Post> getPosts(){
        analyze.count("getPostsAll");
        if(postCache == null || (new Date().getTime() - lastCachedPosts.getTime()) > Const.REFRESH_TIME){
            postCache = getPostList(null);
            lastCachedPosts = new Date();
        }
	return postCache;
    }

    @Override
    public ArrayList<Post> getPosts(int userId) {
        ArrayList<Post> retPosts = new ArrayList<>();
        for(Post tmpPostFromList : postCache){
            if(tmpPostFromList.getUserId() == userId){
                retPosts.add(tmpPostFromList);
            }
        }
        
        if(retPosts.size() > 0){
            return retPosts;
        } else {
            return getPostList(userId);
        }        
        
    }

    @Override
    public ArrayList<Post> getPosts(String searchterm) {
        analyze.count("getPostFromSearchTerm");
        return getPostListBase(searchterm);
    }

    @Override
    public Post getPost(int id) {
        analyze.count("getPostClassFromId");
        for(Post pst: getPostListBase(null)){
            if(pst.getPostId() == id){
                return pst;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Post> getPostList(Integer userId) {
        analyze.count("getPostFromUserId");
        return getPostListBase((Integer)userId);
    }
    
    public ArrayList<Post> getPostListBase(Object userIdOrSearchTerm) {
        analyze.count("getPosts");
        if(postCache == null){
            postCache = new ArrayList<>();
        } else if(postCache.size() > 0 && new Date().getTime() - lastCachedPosts.getTime() > Const.REFRESH_TIME){
            return postCache;
        }
        ArrayList<Post> posts = new ArrayList<Post>();
	String query;
        if(userIdOrSearchTerm == null){
            query = "from cms_post";
        } else {
            if(userIdOrSearchTerm instanceof String){
                query = "from cms_post, cms_user where post_content LIKE '%"+(String)userIdOrSearchTerm +"%'"+
                        "or post_title LIKE '%"+(String)userIdOrSearchTerm+"%' AND post_user_id = user_id";
            } else {
                query = "from cms_post, cms_user where post_user_id = user_id AND post_user_id = "+(Integer)userIdOrSearchTerm;
            }
            
        }
        analyze.count("getPostsDb");
        
        Session session = initSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            List lposts = session.createQuery(query).list(); 
            for (Iterator iterator = 
                              lposts.iterator(); iterator.hasNext();){
                cms_post post = (cms_post) iterator.next(); 
                
                Post p = new Post(
                        post.getId(), 
                        post.getTitle(), 
                        post.getContent(), 
                        post.getUserId(), 
                        post.getPost_date());
                
                posts.add(p);
                
                postCache.add(p);
                lastCachedPosts = new Date();
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
        analyze.count("getUser");
        if(userCache != null && new Date().getTime() - lastCachedUsers.getTime() < Const.REFRESH_TIME){
            
            return userCache;
        }
        
        analyze.count("getUserDb");
        
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
        userCache = users;
        lastCachedUsers = new Date();
        return users;
    }

    @Override
    public int checkUser(String username, String password) {
        analyze.count("checkUser");
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
    public boolean deletePost(int id){
        if(Const.LOG_DEBUG){
            logger.debug("Delete post "+id);
        }
        return execute("DELETE FROM cms_post WHERE post_id="+id);
    }
    
    @Override
    public boolean deletePost(Post post){
        return deletePost(post.getPostId());
    }

    @Override
    public boolean updatePost(int postid, String title, String content) {
        return execute("UPDATE cms_post SET "
                + "post_title='"+title+"'"
                + ", post_content='"+content+"'"
                + " WHERE post_id="+postid+";");
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
        analyze.count("getUserId");
        for(User usr : getUserList()){
            if(usr.getName().equals(nameOrEmail) || usr.getEmail().equals(nameOrEmail)){
                return usr.getUserId();
            }
        }
        return -1;
    }

    @Override
    public User getUser(int id) {
        analyze.count("getUserClassById");
        for(User usr : getUserList()){
            if(usr.getUserId() == id){
                return usr;
            }
        }
        return null;
    }

    @Override
    public boolean deleteUser(int userId){
        return execute("DELETE FROM cms_user WHERE user_id="+userId);
    }
    
    @Override 
    public boolean  deleteUser(User usr){
        return deleteUser(usr.getUserId());
    }
    
    @Override
    public ArrayList<Post> getPinwall(User user) {
        analyze.count("getPinwall");
        if(loadPinsFromDb == false && pinwallCache != null && new Date().getTime() - lastCachedPinwall.getTime() < Const.REFRESH_TIME){
            if(pinwallCache.containsKey(user.getUserId())){
                if(Const.LOG_DEBUG){
                    logger.debug("Pinwall Chache access"+user.getUserId());
                }
                
                return pinwallCache.get(user.getUserId());
            }
        } else if(pinwallCache == null) { 
            pinwallCache = new HashMap<>(); 
        }
        
        if(Const.LOG_DEBUG){
            logger.debug("Pinwall Database access"+user.getUserId());
        }
        analyze.count("getPinwallDb");
        
        ArrayList<Post> posts = new ArrayList<>();
	String query = "from cms_pinwall where pin_user_id = "+user.getUserId();
        Session session = initSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            Query q = session.createQuery(query);
            List lposts = q.list(); 
            for (Iterator iterator = lposts.iterator(); iterator.hasNext();){
                cms_pinwall pin = (cms_pinwall) iterator.next();
                
                posts.add(getPost(pin.getPostId()));
                        
                  

            }
            tx.commit();
            
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
            
        }finally {
            session.close(); 
        }
        
        pinwallCache.put(user.getUserId(), posts);
        lastCachedPinwall = new Date();
        loadPinsFromDb = false;
        return posts;
    }

    @Override
    public boolean addPostToPinwall(User user, Post post) {
        if(Const.LOG_DEBUG){
            new DefaultLog().debug(user.getName());
        }
        return execute("INSERT INTO cms_pinwall (pin_user_id, pin_post_id) VALUES('"+user.getUserId()+"', '"+post.getPostId()+"')");
    }
    
    @Override
    public boolean addPostToPinwall(int userid, int postid) {
        return execute("INSERT INTO cms_pinwall (pin_user_id, pin_post_id) VALUES('"+userid+"', '"+postid+"')");
    }

    @Override
    public boolean deletePostFromPinwall(int pinid) {
        if(Const.LOG_DEBUG){
            logger.debug("delete pin "+pinid);
        }
        loadPinsFromDb = true;
        return execute("DELETE FROM cms_pinwall WHERE pin_id="+pinid);
    }
    
    @Override
    public int getPinId(int userid, int postid) {
        analyze.count("getPinId");
        if(pinwallIdCache != null && new Date().getTime() - lastPinwallIdCache.getTime() > Const.REFRESH_TIME/4){
            Set<String> keys = pinwallIdCache.keySet();
            for(Iterator<String> it = keys.iterator(); it.hasNext();){
                String currentKey = it.next();
                String[] strKeys = currentKey.split(":");
                if(Integer.parseInt(strKeys[0]) == userid && Integer.parseInt(strKeys[1]) == postid){
                    if(Const.LOG_DEBUG){
                        logger.debug("pin id from cache: "+pinwallIdCache.get(currentKey));
                    }
                    return pinwallIdCache.get(currentKey);
                }
                
            }
            
        } else if(pinwallIdCache == null) { 
            pinwallIdCache = new HashMap<>();
        }
        analyze.count("getPinIdDb");
        
        //ArrayList<Post> posts = new ArrayList<>();
        int pinId = -1;
	String query = "from cms_pinwall where pin_user_id="+userid+" AND pin_post_id="+postid;
        Session session = initSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            Query q = session.createQuery(query);
            List lposts = q.list(); 
            for (Iterator iterator = lposts.iterator(); iterator.hasNext();){
                cms_pinwall pin = (cms_pinwall) iterator.next();
                
                pinId = pin.getId();
                if(Const.LOG_DEBUG){
                    logger.debug("pin id from db "+pinId);
                }
                pinwallIdCache.put(userid+":"+postid, pinId);
                lastPinwallIdCache = new Date();

            }
            tx.commit();
            
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
            
        }finally {
            session.close(); 
        }
        
        return pinId;
    }

    @Override
    public ArrayList<Comment> getComments(int postid) {
        analyze.count("getComments");
        if( commentCache != null &&!commentCache.isEmpty() && commentCache.get(postid) != null && !commentCache.get(postid).isEmpty() && new Date().getTime() - lastCacheComment.getTime() > Const.REFRESH_TIME ){
            if(Const.LOG_DEBUG){
                logger.debug("load comments from cache: "+postid);
            }
            return commentCache.get(postid);
        } else {
            if(Const.LOG_DEBUG){
                logger.debug("load comments from db: "+postid);
            }
        }
        analyze.count("getCommentsDb");
        ArrayList<Comment> comments = new ArrayList<>();
	String query = "from cms_comment where comment_post_id="+postid + "order by comment_date desc";
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
        commentCache.put(postid, comments);
        lastCacheComment = new Date();
        
        return comments;
    }

    @Override
    public boolean addComment(Comment comment) {
        return execute("INSERT INTO cms_comment (comment_user_id, comment_content, comment_post_id, comment_date)  values("
                + ""+comment.getUserId()+""
                + ", '"+comment.getContent()+"'"
                + ", "+comment.getPostId()+""
                + ", '"+new java.sql.Date(comment.getDate().getTime()).toString()+"' );");
    }
    
    
    @Override
    public boolean deleteComment(int commentid){
        return execute("DELETE FROM cms_comment WHERE comment_id="+commentid);
    }
    
    @Override
    public boolean deleteComment(Comment comment){
        return deleteComment(comment.getContentId());
    }
    
    private boolean execute(String mysqlQuery){
        analyze.count("execute");
        if(Const.LOG_DEBUG){
            logger.debug("MySQL Query: "+mysqlQuery);
        }
        boolean worked = true;
        Session session = initSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            session.createSQLQuery(mysqlQuery).executeUpdate();

            
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

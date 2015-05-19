/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.database.Database;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.inf.DatabaseControlInf;
import ch.bbw.cms.inf.Log;
import ch.bbw.cms.mock.DefaultLog;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.helper.ClosedList;
import ch.bbw.cms.helper.SessionData;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.*;


/**
 * <b>IndexBean</b>
 * <p>IndexBean is the main Bean in this project. It handles all the functionality of the main
 * page and also provides methods and attributes for other pages.
 * @author patrick
 */
@ManagedBean
@SessionScoped
public class IndexBean {

    @ManagedProperty(value="#{database}")
    private Database database;
    
    private ArrayList<Post> postList;
    private String cssFile;
    private String search = "Search";
    private Post currentPost;
    private Log log = new DefaultLog();
    private SessionData session;
    private String userIdTest;
    private ClosedList<String> cssFls;
    
    
    

    
    public IndexBean() {
	cssFls = new ClosedList<>(new String[]{"main.css", "main_alt_timo.css", "main_alt_pat.css"});
	cssFile = cssFls.next();
       
        //database = new Database();
        session = new SessionData();
        System.out.println("user id:" +session.getUserId());
        
        

	log.debug("PostList: "+postList);
        
    }
    
    public ArrayList<Post> getPostList(){ 
        if(search.equals("") || search.equals("Search")){
            refreshPostList();
        }
        return postList;
    }
    
    public void setPostList(ArrayList<Post> postList){
        this.postList = postList;
    }
    
    public void refreshPostList(){
        log.debug("Refresh");
        postList = getDatabase().getPosts();
    }


    /**
     * @return the cssFile
     */
    public String getCssFile() {
        return cssFile;
    }

    /**
     * @param cssFile the cssFile to set
     */
    public void setCssFile(String cssFile) {
        this.cssFile = cssFile;
    }
    
    public String switchStyles(){
	cssFile = cssFls.next();
        return "main.xhtml";
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }

    public String performSearch(){
        setPostList(getDatabase().getPosts(search));
        log.debug(search+", "+postList.toString());
        return "main.xhtml";
    }
    
    /**
     * @return the currentPost
     */
    public Post getCurrentPost() {
        return currentPost;
    }

    /**
     * @param currentPost the currentPost to set
     */
    public void setCurrentPost(Post currentPost) {
        this.currentPost = currentPost;
    }

    /**
     * @return the userIdTest
     */
    public String getUserIdTest() {
        userIdTest = "User Id: "+session.getUserId();
      
        return userIdTest;
    }

    /**
     * @param userIdTest the userIdTest to set
     */
    public void setUserIdTest(String userIdTest) {
        this.userIdTest = userIdTest;
    }
    
    public String gotoNewPost(){
        session.setCurrentPostId(-1);
        return "create.xhtml";
    }
    
    public String editPost(){
        session.setCurrentPostId(currentPost.getPostId());
        return "create.xhtml";
    }

    public boolean editPostEnabled(){
        try{
            return currentPost.getUserId() == session.getUserId();
        } catch(NullPointerException ex){
            return false;
        }
    }
    
    public boolean isEditor(){
        try{
            return getDatabase().getUser(session.getUserId()).getType().equals(UserType.CONTENT);
        } catch(Exception ex){
            return false;
        }
    }
    
    public String getUserNameFormPost(){
        return "";
    }

    /**
     * @return the database
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(Database database) {
        this.database = database;
        if(postList == null){
            postList = database.getPosts();
        }
        
        try{
	    currentPost = postList.get(0);
	} catch(IndexOutOfBoundsException ex){
	    currentPost = new Post(0, "Ask the admin to get a creator account to publish your own blog!",  "no Posts", -1, new Date());
	}
    }
  
}

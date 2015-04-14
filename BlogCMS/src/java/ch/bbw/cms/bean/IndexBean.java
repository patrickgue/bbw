/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import javax.faces.bean.*;
import ch.bbw.cms.database.DatabaseControl;
import ch.bbw.cms.models.Post;
import java.util.ArrayList;

/**
 *
 * @author patrick
 */
@ManagedBean
@SessionScoped
public class IndexBean {

    private DatabaseControl database;
    private ArrayList<Post> postList;// = new ArrayList<Post>();
    private String cssFile = "main.css";
     
    public IndexBean() {
        database = new DatabaseControl();
        postList = database.getPosts();
        System.out.println("PostList: "+postList);
        
    }
    
    public ArrayList<Post> getPostList(){ 
        refreshPostList();
        return postList;
    }
    
    public void setPostList(ArrayList<Post> postList){
        this.postList = postList;
    }
    
    public void refreshPostList(){
        postList = database.getPosts();
    }
    
    public static void main(String[] args){
        IndexBean testBean = new IndexBean();
        
        for(Post p : testBean.getPostList()){
            System.out.println(p.getTitle()+"\n"+p.getContent());
        }
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
        if("main.css".equals(cssFile)){
            cssFile = "main_alt.css";
        } else {
            cssFile = "main.css";
        }
        return "main.xhtml";
    }
    
}

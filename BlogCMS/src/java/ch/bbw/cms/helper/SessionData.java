/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.helper;

import ch.bbw.cms.database.hibernate.HibernateUtil;
import ch.bbw.cms.models.User;
import javax.faces.context.FacesContext;

/**
 *
 * @author guenthard
 */
public class SessionData {
    private FacesContext context;
    final private String USER_ID = "userid";
    final private String CURRENT_POST_ID = "currentdocumentid";
    final private String DBUTIL_ID = "dbutil";
    

    final private String USER_OBJ = "userobj";
    private User user = null;
    
    
    public SessionData(){
        init();
    }
    
    private void init(){
        context = FacesContext.getCurrentInstance();
    }
    
    
    public int getUserId(){
        init();
        System.out.println(context.getExternalContext().getSessionMap());
        try{
            System.out.println(context.getExternalContext().getSessionMap().get(USER_ID).toString());  
            return Integer.parseInt(context.getExternalContext().getSessionMap().get(USER_ID).toString());
        } catch(NullPointerException | IllegalStateException ex){
            System.out.println("-1");
            return -1; // not logged in...
        }
    }
    
    
    public void setUserId(int id){
        init();
        try{
            context.getExternalContext().getSessionMap().put(USER_ID, id);
        } catch(NullPointerException ex){
            System.err.println("Writing to session didn't work!");
        }
    }
    
    public User getUser(){
        init();
        System.out.println(context.getExternalContext().getSessionMap());
        try{
            System.out.println(context.getExternalContext().getSessionMap().get(USER_ID).toString());  
            return (User) context.getExternalContext().getSessionMap().get(USER_OBJ);
        } catch(NullPointerException | IllegalStateException ex){
            System.out.println("-1");
            return null; // not logged in...
        }
    }
    
    
    public void setUser(User usr){
        init();
        try{
            context.getExternalContext().getSessionMap().put(USER_ID, usr);
        } catch(NullPointerException ex){
            System.err.println("Writing to session didn't work!");
        }
    }
    
    public HibernateUtil getDBUtil(){
        init();
        System.out.println(context.getExternalContext().getSessionMap());
        try{
            return (HibernateUtil) context.getExternalContext().getSessionMap().get(USER_OBJ);
        } catch(NullPointerException | IllegalStateException ex){
            return null; // not logged in...
        }
    }
    
    
    public void setDBUtil(HibernateUtil usr){
        init();
        try{
            context.getExternalContext().getSessionMap().put(DBUTIL_ID, usr);
        } catch(NullPointerException ex){
            System.err.println("Writing to session didn't work!");
        }
    }
    
    
    public int getCurrentPostId(){
        init();
        try{
            return Integer.parseInt(context.getExternalContext().getSessionMap().get(CURRENT_POST_ID).toString());
        } catch(NullPointerException | IllegalStateException ex){
            return -1; // not logged in...
        }
    }
    
    public void setCurrentPostId(int id){
        init();
        try{
            context.getExternalContext().getSessionMap().put(CURRENT_POST_ID, id);
        } catch(NullPointerException ex){
            System.err.println("Writing to session didn't work!");
        }
    }
    
}

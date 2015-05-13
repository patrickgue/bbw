/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.helper;

import javax.faces.context.FacesContext;

/**
 *
 * @author guenthard
 */
public class SessionData {
    private FacesContext context;
    final private String USER_ID = "userid";
    final private String CURRENT_POST_ID = "currentdocumentid";
    
    
    public SessionData(){
        init();
    }
    
    private void init(){
        context = FacesContext.getCurrentInstance();
    }
    
    public int getUserId(){
        init();
        try{
            return Integer.parseInt(context.getExternalContext().getSessionMap().get(USER_ID).toString());
        } catch(NullPointerException | IllegalStateException ex){
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

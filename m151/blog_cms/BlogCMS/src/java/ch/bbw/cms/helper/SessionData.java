/* 
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.helper;

import ch.bbw.cms.bean.SettingsBean;
import ch.bbw.cms.database.hibernate.HibernateUtil;
import ch.bbw.cms.inf.Log;
import ch.bbw.cms.models.User;
import javax.faces.context.FacesContext;

/**
 *
 * This class is used to save data to the session so different beans can access the same data
 */
public class SessionData {
    private FacesContext context;
    final private String USER_ID = "userid";
    final private String CURRENT_POST_ID = "currentdocumentid";
    final private String DBUTIL_ID = "dbutil";
    final private String USER_OBJ = "userobj";
    private User user = null;
    private final Log logger = SettingsBean.logger();
    
    public SessionData(){
        init();
    }
    
    private void init(){
        context = FacesContext.getCurrentInstance();
    }
    
    
    public int getUserId(){
        init();
        
        return getUser().getUserId();
    }
    
    @Deprecated
    public void setUserId(int id){
        init();
        try{
            context.getExternalContext().getSessionMap().put(USER_ID, id);
        } catch(NullPointerException ex){
            System.err.println("Writing to session didn't work!");
        }
    }
    
    public User getUser() throws NullPointerException{
        init();
        try{
            return (User) context.getExternalContext().getSessionMap().get(USER_OBJ);
        } catch(NullPointerException | IllegalStateException ex){
            return null; // not logged in...
        }
    }
    
    
    public void setUser(User usr){
        init();
        try{
            context.getExternalContext().getSessionMap().put(USER_OBJ, usr);
        } catch(NullPointerException ex){
            logger.error("Cannot write User to session", ex);
        }
    }
    
    public HibernateUtil getDBUtil(){
        init();
        System.out.println(context.getExternalContext().getSessionMap());
        try{
            return (HibernateUtil) context.getExternalContext().getSessionMap().get(DBUTIL_ID);
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

/**
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.database.Database;
import ch.bbw.cms.helper.SessionData;
import ch.bbw.cms.mock.DefaultLog;
import ch.bbw.cms.inf.Log;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * Superclass for all main-beans. Includes Database and session access
 */
@SessionScoped
@ManagedBean
public abstract class AllPageBean {
    protected Log logger = new DefaultLog();
    @ManagedProperty(value="#{database}")
    private Database database = new Database();
    private SessionData sessiondata = new SessionData();
    

    
    
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
    }
    
    public String logout(){
        getSessiondata().setUser(null);
        getSessiondata().setCurrentPostId(-1);
        return "login.xhtml";
    }

    /**
     * @return the sessiondata
     */
    public SessionData getSessiondata() {
        return sessiondata;
    }

    /**
     * @param sessiondata the sessiondata to set
     */
    public void setSessiondata(SessionData sessiondata) {
        this.sessiondata = sessiondata;
    }
    
    
}

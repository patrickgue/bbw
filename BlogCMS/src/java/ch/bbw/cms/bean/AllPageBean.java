/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.database.Database;
import ch.bbw.cms.helper.SessionData;
import ch.bbw.cms.mock.DefaultLog;
import ch.bbw.cms.inf.Log;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author guenthard
 */
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
        getSessiondata().setUserId(-1);
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

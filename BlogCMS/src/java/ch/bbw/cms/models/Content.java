/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.bbw.cms.models;

import ch.bbw.cms.database.Database;
import java.util.Date;
import javax.faces.bean.ManagedProperty;

/**
 * This class is used to save different types of data
 * 
 * @author 5ia13nosiegrist
 */
public abstract class Content {
    private User user;
    private int userid;
    private int contentId;
    private String content;
    
    @ManagedProperty(value="#{database}")
    private Database database;
    private Date date;
    
    public Content(Integer contentId, int userId, String content, Date date){
        //database = new Database();
        this.contentId = contentId;
        this.userid = userId;
        this.content = content;
        this.date = date;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return getUser().getUserId();
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        getUser().setUserId(userId);
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the contentId
     */
    public int getContentId() {
        return contentId;
    }

    /**
     * @param contentId the contentId to set
     */
    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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
        this.user = database.getUser(userid);
        this.database = database;
    }


}

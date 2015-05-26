/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.database.Database;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.helper.SessionData;
import ch.bbw.cms.inf.DatabaseControlInf;
import ch.bbw.cms.models.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author guenthard
 */
@ManagedBean
@SessionScoped
public class AdminBean {
    private String databaseconfig;
    
    @ManagedProperty(value = "#{database}")
    private Database db;
    private ArrayList<User> users;
    private SessionData session;
    private boolean render = true;
    private User toChangeUser;
    

    public AdminBean() {
        
        //db = new Database();
        session = new SessionData();
        
        
        
        
    }
    
    public String setDbconf(){
        BufferedWriter writer;
        try{    
            writer = new BufferedWriter(new FileWriter(new File("./dbsettings.ini")));
            writer.write(databaseconfig);
            writer.close();
        } catch (FileNotFoundException ex){} catch (IOException ex1){}
        return "./admin.xhtml";
    }
    

    /**
     * @return the databaseconfig
     */
    public String getDatabaseconfig() {
        return databaseconfig;
    }

    /**
     * @param databaseconfig the databaseconfig to set
     */
    public void setDatabaseconfig(String databaseconfig) {
        this.databaseconfig = databaseconfig;
    }

    /**
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * @return the render
     */
    public boolean isRender() {
        return render;
    }

    /**
     * @param render the render to set
     */
    public void setRender(boolean render) {
        this.render = render;
    }
    
    public boolean isShowError(){
        return !render;
    }
    
    public void setShowError(boolean r){
        render =  !r;
    }
    
    public String editMode(){
        return "admin.xhtml";
    }

    /**
     * @return the toChangeUser
     */
    public User getToChangeUser() {
        return toChangeUser;
    }

    /**
     * @param toChangeUser the toChangeUser to set
     */
    public void setToChangeUser(User toChangeUser) {
        UserType tmp;
        if(toChangeUser.getType().equals(UserType.content)){
            tmp = UserType.technical;
        } else if(toChangeUser.getType().equals(UserType.technical)){
            tmp = UserType.normal;
        } else {
            tmp = UserType.content;
        }
        
        getDb().changeUserType(toChangeUser.getUserId(), tmp);
        toChangeUser.setType(tmp);
        this.toChangeUser = toChangeUser;
    }

    /**
     * @return the db
     */
    public Database getDb() {
        return db;
    }

    /**
     * @param db the db to set
     */
    public void setDb(Database db) {
        this.db = db;
        if(db.getUser(session.getUserId()).getType().equals(UserType.technical)){
            try{
                BufferedReader reader = new BufferedReader(new FileReader(new File("./dbsettings.ini")));
                String buffer;
                while((buffer = reader.readLine()) != null){
                    databaseconfig = buffer;
                }
            } catch (FileNotFoundException ex){} catch (IOException ex1){}
            users = db.getUserList();
        } else {
            render = false;
        }
        
        //debug
        render = true;
        users = db.getUserList();
    }
}

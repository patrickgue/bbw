/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.bbw.cms.database;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This Class is used to connect to the database in the internet. it provides methods which can be called from the application.

 * <b>This class mustn't be directly included into the viewer files (i.e. xhtml files) because of security reasons</b>
 * @author 5ia13paguenthard
 */
public class DatabaseControl {
    private Connection conn;
    
    
    public DatabaseControl(){
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection("jdbc:mysql://db51.netzone.ch/rogerguenthar","rogerguenthar","cms001");
            
        } catch (ClassNotFoundException ex) {  
            ex.printStackTrace();
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    // FIXME: remove on release
    public static void main(String[] args){
        new DatabaseControl();
    }
    
    
}

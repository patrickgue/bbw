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
 *
 * @author 5ia13paguenthard
 */
public class DatabaseControl {
    private Connection conn;
    
    
    public DatabaseControl(){
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection("jdbc:mysql://db51.netzone.ch/rogerguenthar","rogerguenthar","cmd001");
            
        } catch (ClassNotFoundException ex) {  
            ex.printStackTrace();
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        new DatabaseControl();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import javax.faces.bean.*;
import ch.bbw.cms.database.DatabaseControl;

/**
 *
 * @author patrick
 */
@ManagedBean
@RequestScoped
public class IndexBean {

    private DatabaseControl database;
    
    public IndexBean() {
        database = new DatabaseControl();
    }
    
    
    
}

/*
 * Copyright (C) 2016 Patrick
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.eggbacon.app.controller;

import ch.eggbacon.app.entity.Benutzer;
import ch.eggbacon.app.util.HibernateUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Patrick
 */
@ManagedBean
@SessionScoped
public class TestController {
    private String queryString = "";
    private String outputString = "";
    private SessionFactory fc;
    private Session session;

    public TestController() {
        try {
            this.fc = HibernateUtil.getSessionFactory();
            session = fc.openSession();

            
        } catch (Exception ex) {
            
            ex.printStackTrace();
        }
    }

    
    
    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getOutputString() {
        return outputString;
    }

    public void setOutputString(String outputString) {
        this.outputString = outputString;
    }

    
    public String runQuery(){
        try {
            String str = "";
            List<Object> list = session.createQuery(getQueryString()).list();
            for(Object o : list){
                System.out.println(o.toString());
                str += o.toString() + "<br/>";
            }
            setOutputString(str);
        } catch (Exception ex) {
            ex.printStackTrace();
            setOutputString("<i>Error</i>");
        }
        return "test.xhtml";
    }
  
    
    
    
}

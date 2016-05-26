/*
 * Copyright (C) 2016 guenthard
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
package ch.eggbacon.app.service;

import ch.eggbacon.app.util.HibernateUtil;
import ch.eggbacon.util.logger.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author guenthard
 */
public class DatabaseService<T> {
    
    private SessionFactory sf;
    private Session session;
    private final Logger LOG = new Logger(this.getClass().getName());

    public DatabaseService() {
        sf = HibernateUtil.getSessionFactory();
        try {
            session = sf.getCurrentSession();
            LOG.info("Session Found");
        } catch (HibernateException ex) {
            session = sf.openSession();
            LOG.info("New Session created");
        }
        
    }
    
    public Session getSession(){
        return this.session;
    }
    
    
    public boolean persist(T obj){
        try {
            
            getSession().getTransaction().begin();
            
            getSession().save(obj);
            if(!getSession().getTransaction().wasCommitted()){
                getSession().getTransaction().commit();
            } else {
                getSession().getTransaction().rollback();
            }
            LOG.info("Persistance successful" + obj.toString());
            return true;
        } catch(Exception ex) {
            LOG.error("Persistance failed: " + ex);
            return false;
        }
    }
    
    public boolean delete(T obj){
        try {
            getSession().getTransaction().begin();
            
            getSession().delete(obj);
            
            if(!getSession().getTransaction().wasCommitted()) {
                getSession().getTransaction().commit();
            } else {
                getSession().getTransaction().rollback();
            }
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
    
    
    
    
}

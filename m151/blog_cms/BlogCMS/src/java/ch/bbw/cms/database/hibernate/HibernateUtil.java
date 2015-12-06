/* 
 * source: stackoverflow.com
 * integrated by: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.database.hibernate;

import ch.bbw.cms.bean.SettingsBean;
import ch.bbw.cms.inf.Log;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.hibernate.SessionFactory;  
import org.hibernate.cfg.Configuration;  
import org.hibernate.service.ServiceRegistry;  
import org.hibernate.service.ServiceRegistryBuilder;  
  
@ManagedBean
@ApplicationScoped
public class HibernateUtil {  
      
    private final SessionFactory sessionFactory;  
    private final ServiceRegistry serviceRegistry;  
    private final Log logger = SettingsBean.logger();
      
    public HibernateUtil() {  
        Configuration conf = new Configuration();  
        conf.configure();  
        serviceRegistry = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();  
        try {  
            sessionFactory = conf.buildSessionFactory(serviceRegistry);  
        } catch (Exception e) {  
            logger.error("Initial SessionFactory creation failed: ", e);  
            throw new ExceptionInInitializerError(e);  
        }         
    }  
      
    public SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
}  
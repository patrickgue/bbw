package ch.bbw.cms.database.hibernate;

import org.hibernate.SessionFactory;  
import org.hibernate.cfg.Configuration;  
import org.hibernate.service.ServiceRegistry;  
import org.hibernate.service.ServiceRegistryBuilder;  
  
public class HibernateUtil {  
      
    private final SessionFactory sessionFactory;  
    private final ServiceRegistry serviceRegistry;  
      
    public HibernateUtil() {  
        Configuration conf = new Configuration();  
        conf.configure();  
        serviceRegistry = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();  
        try {  
            sessionFactory = conf.buildSessionFactory(serviceRegistry);  
        } catch (Exception e) {  
            System.err.println("Initial SessionFactory creation failed." + e);  
            throw new ExceptionInInitializerError(e);  
        }         
    }  
      
    public SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
}  
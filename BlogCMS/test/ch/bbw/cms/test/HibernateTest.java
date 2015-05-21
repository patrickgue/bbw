package ch.bbw.cms.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import ch.bbw.cms.database.hibernate.*;
import ch.bbw.cms.models.User;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author guenthard
 */
public class HibernateTest {
    
    private SessionFactory factory;
    private HibernateUtil util;
   
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        util = new HibernateUtil();
        factory = util.getSessionFactory();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testRead(){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List users = session.createQuery("from cms_user").list(); 
            for (Iterator iterator = 
                              users.iterator(); iterator.hasNext();){
                User user = (User) iterator.next(); 
                System.out.print("Name: " + user.getName()); 
                System.out.print("  Email: " + user.getEmail()); 
                System.out.println("  Type: " + user.getUserTypeString()); 
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
            fail();
        }finally {
            session.close(); 
        }
    }
}

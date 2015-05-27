/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.test;

import ch.bbw.cms.database.HibernateDatabase;
import ch.bbw.cms.inf.DatabaseControlInf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author guenthard
 */
public class HibernateDatabaseTest {
    
    private DatabaseControlInf database;
    
    public HibernateDatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        database = new HibernateDatabase();
    }
    
    @After
    public void tearDown() {
    }

    
    
    @Test
    public void testPostList() {
        assertNotSame(null, database.getPostList(null));
    }
    
    @Test
    public void testPostListLength(){
        assertEquals(1, database.getPostList(null).size());
    }
    
    
}

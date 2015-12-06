/* 
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.test;

import ch.bbw.cms.database.HibernateDatabase;
import ch.bbw.cms.inf.DatabaseControlInf;
import javax.faces.bean.ManagedProperty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Used to test hibernate database class
 */
public class HibernateDatabaseTest {
    
    @ManagedProperty(value = "#{hibernateUtil}")
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
        assertEquals(15, database.getPostList(null).size());
    }
    
    
}

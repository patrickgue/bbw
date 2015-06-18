/* 
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.test;

import ch.bbw.cms.helper.Analyzer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * Tests analyze logging
 */
public class TestAnalyze {
   private static Analyzer anyz;
    
    @BeforeClass
    public static void setUp(){
        anyz = new Analyzer("test");
    }
    
    @Test
    public void testPut(){
        if(!anyz.count("someVal")){
            Assert.fail();
        }
    }
    
}

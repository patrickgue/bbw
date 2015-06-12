/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.test;

import ch.bbw.cms.helper.Analyzer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author guenthard
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

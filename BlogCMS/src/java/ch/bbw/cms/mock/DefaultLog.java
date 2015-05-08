/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.mock;

import ch.bbw.cms.inf.Log;

/**
 *
 * @author guenthard
 */
public class DefaultLog implements Log{

    @Override
    public void debug(String msg) {
        System.out.println("[log] [ debug ] "+msg);
    }

    @Override
    public void error(String msg, Throwable thr) {
        System.out.println("[log] [ error ] "+msg);
        thr.printStackTrace();
    }

    @Override
    public void warning(String msg) {
        System.out.println("[log] [warning] "+msg);
    }
    
}

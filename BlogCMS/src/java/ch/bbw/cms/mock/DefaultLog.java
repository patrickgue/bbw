/* 
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.mock;

import ch.bbw.cms.inf.Log;
/**
 * Implements Log interface. Simple log class, prints log statements to stdout
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

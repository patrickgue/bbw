/* 
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.inf;

/**
 *
 * Interface which describes logging classes
 */
public interface Log {
    public void debug(String msg);
    public void error(String msg, Throwable thr);
    public void warning(String msg);
}

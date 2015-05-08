/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.inf;

/**
 *
 * @author guenthard
 */
public interface Log {
    public void debug(String msg);
    public void error(String msg, Throwable thr);
    public void warning(String msg);
}

/* 
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.helper.ClosedList;
import ch.bbw.cms.inf.Log;
import ch.bbw.cms.mock.DefaultLog;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * Bean for several settings used in various xhtml pages
 */
@ManagedBean
@SessionScoped
public class SettingsBean {
    private ClosedList<String> cssFls;
    private String cssFile;

    public SettingsBean() {
        cssFls = new ClosedList<>(new String[]{"main.css", "main_alt_timo.css", "main_alt_pat.css"});
	cssFile = cssFls.next();
    }
    
    /**
     * @return the cssFile
     */
    public String getCssFile() {
        return cssFile;
    }

    /**
     * @param cssFile the cssFile to set
     */
    public void setCssFile(String cssFile) {
        this.cssFile = cssFile;
    }
    
    public String switchStyles(){
	cssFile = cssFls.next();
        return "main.xhtml";
    }
    
    public static Log logger(){
        return new DefaultLog();
    }
}

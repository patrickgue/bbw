/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import javax.faces.bean.*;
import ch.bbw.cms.database.DatabaseControl;
import ch.bbw.cms.mock.DatabaseControlMock;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.inf.DatabaseControlInf;
import ch.bbw.cms.inf.Log;
import ch.bbw.cms.mock.DefaultLog;
import java.util.ArrayList;


/**
 *
 * @author patrick
 */
@ManagedBean
@SessionScoped
public class CreationBean {
    private String title;
    private String postcontent;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the postcontent
     */
    public String getPostcontent() {
        return postcontent;
    }

    /**
     * @param postcontent the postcontent to set
     */
    public void setPostcontent(String postcontent) {
        this.postcontent = postcontent;
    }
}

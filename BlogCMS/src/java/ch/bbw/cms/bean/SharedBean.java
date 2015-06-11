/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.models.Post;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author guenthard
 */
@RequestScoped
@ManagedBean
public class SharedBean extends AllPageBean{
    private Post currentPost;
    public SharedBean(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String param = (String) facesContext.getExternalContext().getRequestParameterMap().get("postid");
        currentPost = getDatabase().getPost(Integer.parseInt(param));
    }

    /**
     * @return the currentPost
     */
    public Post getCurrentPost() {
        return currentPost;
    }

    /**
     * @param currentPost the currentPost to set
     */
    public void setCurrentPost(Post currentPost) {
        this.currentPost = currentPost;
    }
}

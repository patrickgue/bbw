/* 
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.bean;

import ch.bbw.cms.models.Post;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * Bean used to display a post which was shared. Gets post id with GET paramenter
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

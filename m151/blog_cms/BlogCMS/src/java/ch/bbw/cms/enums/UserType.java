/* 
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.enums;

/**
 * Defines the Type of the User
 * 
 * NORMAL: This user can read a post and pin it to the pinwall of his or her account
 * CONTENT: This user can do the same as the normal user and also create and edit posts
 * TECHNICAL: This user can do admin stuff like deleting users or changeing the style
 */
public enum UserType{
    normal("normal"), content("content"), technical("technical");

    public static UserType getUserTypeFromString(String type) {
        if(type.equals(normal.getType())){
            return normal;
        } else if(type.equals(content.getType())){
            return content;
        } else if(type.equals(technical.getType())){
            return technical;
        } else {
            return null;
        }
    }
    
    private String type; 
    
    UserType(String type){
        this.type = type;
    }
    
    public String getType(){
        return this.type;
    }
    
};
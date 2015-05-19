package ch.bbw.cms.enums;

/**
 * Defines the Type of the User
 * 
 * NORMAL: This user can read a post and pin it to the pinwall of his or her account
 * CONTENT: This user can do the same as the normal user and also create and edit posts
 * TECHNICAL: This user can do admin stuff like deleting users or changeing the style
 */
public enum UserType{
    NORMAL("normal"), CONTENT("content"), TECHNICAL("technical");

    public static UserType getUserTypeFromString(String type) {
        if(type.equals(NORMAL.getType())){
            return NORMAL;
        } else if(type.equals(CONTENT.getType())){
            return CONTENT;
        } else if(type.equals(TECHNICAL.getType())){
            return TECHNICAL;
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
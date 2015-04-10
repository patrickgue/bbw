package ch.bbw.cms.enums;


/**
 * Used to define the Gender of the user
 * 
 * OTHER should be used for people who fall outside of the binary gender. FEMALE and MALE should not be 
 * limited to cis-gender people but to people who define them as the specified gender (i.e. social, not biological gender).
 * 
 * @author 5ia13paguenthard
 */
public enum UserGender{
    FEMALE("female"), MALE("male"), OTHER("other");
    
    public static UserGender getUserTypeFromString(String type) {
        if(type.equals(MALE.getGenderName())){
            return MALE;
        } else if(type.equals(FEMALE.getGenderName())){
            return FEMALE;
        } else if(type.equals(OTHER.getGenderName())){
            return OTHER;
        } else {
            return null;
        }
    }
    
    String genderName;
    
    UserGender(String genderName){
        this.genderName = genderName;
    }
    
    public String getGenderName(){
        return this.genderName;
    }
}
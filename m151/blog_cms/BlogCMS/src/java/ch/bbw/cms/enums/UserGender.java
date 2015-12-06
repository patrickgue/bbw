/* 
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.enums;

/**
 * Used to define the Gender of the user
 * 
 * OTHER should be used for people who fall outside of the binary gender or people who don't want other people to 
 * know their gender. FEMALE and MALE should not be limited to cis-gender people but to people who define them as 
 * 
 * 
 */
public enum UserGender{
    female("female"), male("male"), other("other");
    
    public static UserGender getUserTypeFromString(String type) {
        type = type.toLowerCase();
        if(type.equals(male.getGenderName())){
            return male;
        } else if(type.equals(female.getGenderName())){
            return female;
        } else if(type.equals(other.getGenderName())){
            return other;
        } else {
            return null;
        }
    }
    
    String genderName;
    
    UserGender(String genderName){
        genderName = genderName.toLowerCase();
        this.genderName = genderName;
    }
    
    public String getGenderName(){
        return this.genderName;
    }
}
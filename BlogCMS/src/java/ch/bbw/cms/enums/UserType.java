package ch.bbw.cms.enums;

/**
 * Defines the Type of the User
 * 
 * NORMAL: This user can read a post and pin it to the pinwall of his or her account
 * CONTENT: This user can do the same as the normal user and also create and edit posts
 * TECHNICAL: This user can do admin stuff like deleting users or changeing the style
 */
public enum UserType{
    NORMAL, CONTENT, TECHNICAL;
};
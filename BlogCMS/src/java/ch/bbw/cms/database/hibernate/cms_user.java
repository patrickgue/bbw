/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.database.hibernate;

import ch.bbw.cms.enums.UserGender;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.models.User;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author guenthard
 */

@Entity
@Table
public class cms_user {
    @Id
    @GeneratedValue
    private Integer user_id;
    private String user_name;
    private String user_email;
    private String user_password;
    @Enumerated(EnumType.STRING)
    private UserType user_type;
    private String user_bio;
    private Integer user_age;
    @Enumerated(EnumType.STRING)
    private UserGender user_gender;

    public cms_user(){}
    
    public cms_user(Integer id, String name, String password, String email, UserGender gender, UserType type, String bio, Integer age){
        this.user_id = id;
        this.user_name = name;
        this.user_password = password;
        this.user_email = email;
        this.user_gender = gender;
        this.user_type = type;
        this.user_bio = bio;
        this.user_age = age;
    }
    
    
    public User toUser(){
        return new User(getId(), getName(), getPassword(), getEmail(), getGender(), getType(), getBio(), getAge());
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return user_id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.user_id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return user_name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.user_name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return user_email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.user_email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return user_password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.user_password = password;
    }

    /**
     * @return the type
     */
    public UserType getType() {
        return user_type;
    }

    /**
     * @param type the type to set
     */
    public void setType(UserType type) {
        this.user_type = type;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return user_bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.user_bio = bio;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return user_age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.user_age = age;
    }

    /**
     * @return the gender
     */
    public UserGender getGender() {
        return user_gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(UserGender gender) {
        this.user_gender = gender;
    }
    
    
    
}

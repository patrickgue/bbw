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
public class DBUser {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType type;
    private String bio;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    public DBUser(){}
    
    public DBUser(Integer id, String name, String password, String email, UserGender gender, UserType type, String bio, Integer age){
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.type = type;
        this.bio = bio;
        this.age = age;
    }
    
    
    public User toUser(){
        return new User(getId(), getName(), getPassword(), getEmail(), getGender(), getType(), getBio(), getAge());
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the type
     */
    public UserType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(UserType type) {
        this.type = type;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return the gender
     */
    public UserGender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(UserGender gender) {
        this.gender = gender;
    }
    
    
}

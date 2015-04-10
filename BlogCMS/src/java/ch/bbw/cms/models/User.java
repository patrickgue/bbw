/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.bbw.cms.models;

import ch.bbw.cms.enums.*;

/**
 * 
 * @author 5ia13tibonomelli, 5ia13paguenthard
 */
public class User {
    private String name;
    private String password;
    private String email;
    private UserType type;
    private UserGender gender;
    private String bio;
    private int age;


    private int id;

    public void setName(String name){
	this.name = name;
    }

    public String getName(){
	return this.name;
    }

    public void setPassword(String password){
	this.password = password;
    }

    public String getPassword(){
	return this.password;
    }

    public void setEmail(String email){
	this.email = email;
    }

    public String getEmail(){
	return this.email;
    }

    public void setType(UserType type){
	this.type = type;
    }

    public UserType getType(){
	return this.type;
    }

    public void setBio(String bio){
	this.bio = bio;
    }

    public String getBio(){
	return this.bio;
    }

    public UserGender getGender(){
	return this.gender;
    }

    public void setGender(UserGender gender){
	this.gender = gender;
    }

    public void setAge(int age){
	this.age = age;
    }

    public void setUserId(int id){
	this.id = id;
    }

    public int getUserId(){
	return id;
    }
}

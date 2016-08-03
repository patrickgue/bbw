/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.eggbacon.app.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Patrick
 */
@Entity
public class Benutzer implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="UserID")
    private Long userId;
    
    @Column(name="Benutzer")
    private String benutzer;
    
    @Column(name="Passwort")
    private String passwort;
    
    @Column(name="GanzerName")
    private String ganzerName;

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    

    /**
     * @return the benutzer
     */
    public String getBenutzer() {
        return benutzer;
    }

    /**
     * @param benutzer the benutzer to set
     */
    public void setBenutzer(String benutzer) {
        this.benutzer = benutzer;
    }

    /**
     * @return the passwort
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * @param passwort the passwort to set
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    /**
     * @return the ganzerName
     */
    public String getGanzerName() {
        return ganzerName;
    }

    /**
     * @param ganzerName the ganzerName to set
     */
    public void setGanzerName(String ganzerName) {
        this.ganzerName = ganzerName;
    }

    @Override
    public String toString() {
        return "Benutzer{" + "userId=" + userId + ", benutzer=" + benutzer + ", passwort=" + passwort + ", ganzerName=" + ganzerName + '}';
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.userId);
        hash = 31 * hash + Objects.hashCode(this.benutzer);
        hash = 31 * hash + Objects.hashCode(this.passwort);
        hash = 31 * hash + Objects.hashCode(this.ganzerName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Benutzer other = (Benutzer) obj;
        if (!Objects.equals(this.benutzer, other.benutzer)) {
            return false;
        }
        if (!Objects.equals(this.passwort, other.passwort)) {
            return false;
        }
        if (!Objects.equals(this.ganzerName, other.ganzerName)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return true;
    }
    
    
    
}

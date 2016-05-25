/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.eggbacon.app.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Patrick
 */
@Entity
@Table(name="Buchung")
public class Buchung {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BuchungID")
    private Long buchungId;
    
    @Column(name="PersID")
    @ManyToOne
    private Person person;
    
    @Column(name="Ankunft")
    private Date ankunft;
    
    
    @Column(name="Abreise")
    private Date abreise;

    public Long getBuchungId() {
        return buchungId;
    }

    public void setBuchungId(Long buchungId) {
        this.buchungId = buchungId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getAnkunft() {
        return ankunft;
    }

    public void setAnkunft(Date ankunft) {
        this.ankunft = ankunft;
    }

    public Date getAbreise() {
        return abreise;
    }

    public void setAbreise(Date abreise) {
        this.abreise = abreise;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.buchungId);
        hash = 79 * hash + Objects.hashCode(this.person);
        hash = 79 * hash + Objects.hashCode(this.ankunft);
        hash = 79 * hash + Objects.hashCode(this.abreise);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Buchung other = (Buchung) obj;
        if (!Objects.equals(this.buchungId, other.buchungId)) {
            return false;
        }
        if (!Objects.equals(this.person, other.person)) {
            return false;
        }
        if (!Objects.equals(this.ankunft, other.ankunft)) {
            return false;
        }
        if (!Objects.equals(this.abreise, other.abreise)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Buchung{" + "buchungId=" + buchungId + ", person=" + person + ", ankunft=" + ankunft + ", abreise=" + abreise + '}';
    }
    
    
    
    
}

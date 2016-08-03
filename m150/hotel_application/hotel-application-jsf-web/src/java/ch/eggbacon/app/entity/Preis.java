/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.eggbacon.app.entity;

import java.io.Serializable;
import java.util.Date;
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
public class Preis implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PreisID")
    private Long preisId;
    
    @Column(name="Preis")
    private Double preis;
    
    @Column(name="Rabatt")
    private Double rabatt;
    
    @Column(name="Datum")
    private Date datum;

    @Column(name="LeistungID")
    private Leistung leistung;

    public Long getPreisId() {
        return preisId;
    }

    public void setPreisId(Long preisId) {
        this.preisId = preisId;
    }

    public Double getPreis() {
        return preis;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    public Double getRabatt() {
        return rabatt;
    }

    public void setRabatt(Double rabatt) {
        this.rabatt = rabatt;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Leistung getLeistung() {
        return leistung;
    }

    public void setLeistung(Leistung leistung) {
        this.leistung = leistung;
    }

    @Override
    public String toString() {
        return "Preis{" + "preisId=" + preisId + ", preis=" + preis + ", rabatt=" + rabatt + ", datum=" + datum + ", leistung=" + leistung + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.preisId);
        hash = 53 * hash + Objects.hashCode(this.preis);
        hash = 53 * hash + Objects.hashCode(this.rabatt);
        hash = 53 * hash + Objects.hashCode(this.datum);
        hash = 53 * hash + Objects.hashCode(this.leistung);
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
        final Preis other = (Preis) obj;
        if (!Objects.equals(this.preisId, other.preisId)) {
            return false;
        }
        if (!Objects.equals(this.preis, other.preis)) {
            return false;
        }
        if (!Objects.equals(this.rabatt, other.rabatt)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        if (!Objects.equals(this.leistung, other.leistung)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
}

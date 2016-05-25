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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Patrick
 */
@Entity
@Table(name="position")
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PositionID")
    private Long positionId;

    @ManyToOne
    @Column(name="BuchungID")
    private Buchung buchung;
    
    @ManyToOne
    @Column(name="LeistungID")
    private Leistung leistung;
    
    @ManyToOne
    @Column(name="UserID")
    private Benutzer benutzer;
    
    @Column(name="Anzahl")
    private Long anzahl;

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Buchung getBuchung() {
        return buchung;
    }

    public void setBuchung(Buchung buchung) {
        this.buchung = buchung;
    }

    public Leistung getLeistung() {
        return leistung;
    }

    public void setLeistung(Leistung leistung) {
        this.leistung = leistung;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Long getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(Long anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    public String toString() {
        return "Position{" + "positionId=" + positionId + ", buchung=" + buchung + ", leistung=" + leistung + ", benutzer=" + benutzer + ", anzahl=" + anzahl + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.positionId);
        hash = 17 * hash + Objects.hashCode(this.buchung);
        hash = 17 * hash + Objects.hashCode(this.leistung);
        hash = 17 * hash + Objects.hashCode(this.benutzer);
        hash = 17 * hash + Objects.hashCode(this.anzahl);
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
        final Position other = (Position) obj;
        if (!Objects.equals(this.positionId, other.positionId)) {
            return false;
        }
        if (!Objects.equals(this.buchung, other.buchung)) {
            return false;
        }
        if (!Objects.equals(this.leistung, other.leistung)) {
            return false;
        }
        if (!Objects.equals(this.benutzer, other.benutzer)) {
            return false;
        }
        if (!Objects.equals(this.anzahl, other.anzahl)) {
            return false;
        }
        return true;
    }
    

    
    
  
    
}

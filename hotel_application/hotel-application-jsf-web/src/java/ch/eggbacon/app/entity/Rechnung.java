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
public class Rechnung implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="RechnungId")
    private Long rechnungId;
    
    @Column(name="BuchungId")
    private Buchung buchung;
    
    @Column(name="ErstellungsDatum")
    private Date erstellungsDatum;
    
    @Column(name="ZahlungsfristDatum")
    private Date zahlungsfristDatum;
    
    @Column(name="ZahlungseingangsDatum")
    private Date zahlungseingangsDatum;

    public Long getRechnungId() {
        return rechnungId;
    }

    public void setRechnungId(Long rechnungId) {
        this.rechnungId = rechnungId;
    }

    
    public Buchung getBuchung() {
        return buchung;
    }

    public void setBuchung(Buchung buchung) {
        this.buchung = buchung;
    }

    public Date getErstellungsDatum() {
        return erstellungsDatum;
    }

    public void setErstellungsDatum(Date erstellungsdatum) {
        this.erstellungsDatum = erstellungsdatum;
    }

    public Date getZahlungsfristDatum() {
        return zahlungsfristDatum;
    }

    public void setZahlungsfristDatum(Date zahlungsfristDatum) {
        this.zahlungsfristDatum = zahlungsfristDatum;
    }

    public Date getZahlungseingangsDatum() {
        return zahlungseingangsDatum;
    }

    public void setZahlungseingangsDatum(Date zahlungseingangsDatum) {
        this.zahlungseingangsDatum = zahlungseingangsDatum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.rechnungId);
        hash = 11 * hash + Objects.hashCode(this.buchung);
        hash = 11 * hash + Objects.hashCode(this.erstellungsDatum);
        hash = 11 * hash + Objects.hashCode(this.zahlungsfristDatum);
        hash = 11 * hash + Objects.hashCode(this.zahlungseingangsDatum);
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
        final Rechnung other = (Rechnung) obj;
        if (!Objects.equals(this.buchung, other.buchung)) {
            return false;
        }
        if (!Objects.equals(this.rechnungId, other.rechnungId)) {
            return false;
        }
        if (!Objects.equals(this.erstellungsDatum, other.erstellungsDatum)) {
            return false;
        }
        if (!Objects.equals(this.zahlungsfristDatum, other.zahlungsfristDatum)) {
            return false;
        }
        if (!Objects.equals(this.zahlungseingangsDatum, other.zahlungseingangsDatum)) {
            return false;
        }
        return true;
    }
    
    
    
}

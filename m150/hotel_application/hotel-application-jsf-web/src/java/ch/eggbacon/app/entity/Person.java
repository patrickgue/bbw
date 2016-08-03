/*
 * Copyright (C) 2016 Patrick
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.eggbacon.app.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Patrick
 */
@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PersID")
    private Long persId;
    
    @Column(name="Vorname")
    private String vorname;
    
    @Column(name="Nachname")
    private String nachname;
    
    @Column(name="Strasse")
    private String Strasse;
    
    @Column(name="Ort")
    private String ort;
    
    @Column(name="Anrede")
    private String anrede;
    
    @Column(name="Privattelefon")
    private String privattelefon;
    
    @Column(name="Eingabedatum")
    private Date eingabedatum;

    public Long getPersId() {
        return persId;
    }

    public void setPersId(Long persId) {
        this.persId = persId;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getStrasse() {
        return Strasse;
    }

    public void setStrasse(String Strasse) {
        this.Strasse = Strasse;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getPrivattelefon() {
        return privattelefon;
    }

    public void setPrivattelefon(String privattelefon) {
        this.privattelefon = privattelefon;
    }

    public Date getEingabedatum() {
        return eingabedatum;
    }

    public void setEingabedatum(Date eingabedatum) {
        this.eingabedatum = eingabedatum;
    }

    @Override
    public String toString() {
        return "Person{" + "persId=" + persId + ", vorname=" + vorname + ", nachname=" + nachname + ", Strasse=" + Strasse + ", ort=" + ort + ", anrede=" + anrede + ", privattelefon=" + privattelefon + ", eingabedatum=" + eingabedatum + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.persId);
        hash = 71 * hash + Objects.hashCode(this.vorname);
        hash = 71 * hash + Objects.hashCode(this.nachname);
        hash = 71 * hash + Objects.hashCode(this.Strasse);
        hash = 71 * hash + Objects.hashCode(this.ort);
        hash = 71 * hash + Objects.hashCode(this.anrede);
        hash = 71 * hash + Objects.hashCode(this.privattelefon);
        hash = 71 * hash + Objects.hashCode(this.eingabedatum);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.persId, other.persId)) {
            return false;
        }
        if (!Objects.equals(this.vorname, other.vorname)) {
            return false;
        }
        if (!Objects.equals(this.nachname, other.nachname)) {
            return false;
        }
        if (!Objects.equals(this.Strasse, other.Strasse)) {
            return false;
        }
        if (!Objects.equals(this.ort, other.ort)) {
            return false;
        }
        if (!Objects.equals(this.anrede, other.anrede)) {
            return false;
        }
        if (!Objects.equals(this.privattelefon, other.privattelefon)) {
            return false;
        }
        if (!Objects.equals(this.eingabedatum, other.eingabedatum)) {
            return false;
        }
        return true;
    }
    
    
    
}

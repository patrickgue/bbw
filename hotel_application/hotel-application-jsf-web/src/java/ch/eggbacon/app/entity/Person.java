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
    
    @Column(name="Vorname")
    private Date eingabedatum;
}

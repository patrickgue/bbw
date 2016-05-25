/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.eggbacon.app.entity;

import java.util.Date;
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
}

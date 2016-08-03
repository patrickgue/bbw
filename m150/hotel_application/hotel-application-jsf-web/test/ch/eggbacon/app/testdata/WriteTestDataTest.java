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
package ch.eggbacon.app.testdata;

import ch.eggbacon.app.entity.Benutzer;
import ch.eggbacon.app.entity.Buchung;
import ch.eggbacon.app.entity.Leistung;
import ch.eggbacon.app.entity.Person;
import ch.eggbacon.app.entity.Position;
import ch.eggbacon.app.entity.Preis;
import ch.eggbacon.app.interf.BenutzerService;
import ch.eggbacon.app.interf.BuchungService;
import ch.eggbacon.app.interf.LeistungService;
import ch.eggbacon.app.interf.PersonService;
import ch.eggbacon.app.interf.PositionService;
import ch.eggbacon.app.interf.PreisService;
import ch.eggbacon.app.service.BenutzerServiceImpl;
import ch.eggbacon.app.service.BuchungServiceImpl;
import ch.eggbacon.app.service.LeistungServiceImpl;
import ch.eggbacon.app.service.PersonServiceImpl;
import ch.eggbacon.app.service.PositionServiceImpl;
import ch.eggbacon.app.service.PreisServiceImpl;
import ch.eggbacon.app.util.DateUtils;
import ch.eggbacon.app.util.RandomDate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrick
 */
public class WriteTestDataTest {
    
    private final int NUMBER_OF_BUCHUNG = 10;
    private final int NUMBER_OF_POSITION = 100;
    
    private PersonService personService;
    private LeistungService leistungService;
    private BuchungService buchungService;
    private PositionService positionService;
    private BenutzerService benutzerService;
    private PreisService preisService;
    
    private List<Person> personen;
    private List<Leistung> leistungen;
    private List<Buchung> buchungen;
    private List<Benutzer> benutzer;
    private List<Position> positionen;
    
    
    
    public WriteTestDataTest() {
        personService = new PersonServiceImpl();
        leistungService = new LeistungServiceImpl();
        positionService = new PositionServiceImpl();
        benutzerService = new BenutzerServiceImpl();
        buchungService = new BuchungServiceImpl();
        preisService = new PreisServiceImpl();
        
        personen = personService.searchAllPerson();
        leistungen = leistungService.getAllLeistung();
        benutzer = benutzerService.searchAllBenutzer();
        buchungen = new ArrayList<>();
        positionen = new ArrayList<>();
    }
    
    

    private void writeBuchungen(){
        RandomDate randDate = new RandomDate(LocalDate.of(2011, 06, 01), LocalDate.of(2021,06,01));
        Random rand = new Random();
        for(int i = 0; i < NUMBER_OF_BUCHUNG; i++){
            Buchung b = new Buchung();
            b.setPerson(personen.get(rand.nextInt(personen.size())));
            b.setAnkunft(DateUtils.asDate(randDate.nextDate()));
            b.setAbreise(DateUtils.asDate(randDate.nextDate()));
            buchungen.add(b);
            if(!buchungService.persist(b))
                fail();
            
        }
    }
    
    private void writePosition(){
        Random rand = new Random();
        RandomDate randDate = new RandomDate(LocalDate.of(2011, 06, 01), LocalDate.of(2021,06,01));
        for(int i = 0; i < NUMBER_OF_POSITION; i++){
            Position p = new Position();
            p.setAnzahl(rand.nextInt(10) + 1l);
            p.setBuchung(buchungen.get(rand.nextInt(buchungen.size())));
            p.setLeistung(leistungen.get(rand.nextInt(leistungen.size())));
            p.setBenutzer(benutzer.get(rand.nextInt(benutzer.size())));
            p.setDatum(DateUtils.asDate(randDate.nextDate()));
            positionen.add(p);
            if(!positionService.persist(p))
                fail();
            
            
        }
    }
    
    private void writePreis(){
        Random rand = new Random();
        for(int i = 0; i < positionen.size(); i++){
            Preis p = new Preis();
            p.setDatum(positionen.get(i).getDatum());
            p.setLeistung(leistungen.get(rand.nextInt(leistungen.size())));
            p.setPreis(rand.nextInt(1000) * 1.1);
            p.setRabatt(0.0);
            if(!preisService.persist(p))
                    fail();
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void writeToDb(){
        writeBuchungen();
        writePosition();
        writePreis();
    }

}

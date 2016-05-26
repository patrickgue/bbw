/*
 * Copyright (C) 2016 guenthard
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
package ch.eggbacon.app.controller;

import ch.eggbacon.app.entity.Person;
import ch.eggbacon.app.interf.PersonService;
import ch.eggbacon.app.service.PersonServiceImpl;
import ch.eggbacon.app.util.HibernateUtil;
import ch.eggbacon.util.logger.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author guenthard
 */
@ManagedBean
@SessionScoped
public class PersonController {
    
    private Logger LOG = new Logger(this.getClass().getName()); 

    
    private List<Person> personList;
    private PersonServiceImpl service;
    private Person newPerson;
    private SimpleDateFormat sdtF = new SimpleDateFormat("dd.MM.yyyy",Locale.GERMAN);
    
    private String filterString;

    public PersonController () {
        
        service = new PersonServiceImpl();

        personList = service.searchAllPerson();
        newPerson = new Person();

    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    /**
     * @return the newPerson
     */
    public Person getNewPerson() {
        return newPerson;
    }

    /**
     * @param newPerson the newPerson to set
     */
    public void setNewPerson(Person newPerson) {
        this.newPerson = newPerson;
    }
    
    
    public String createNewPerson(){
        newPerson.setEingabedatum(new Date());
        if(service.persist(newPerson)){
            loadPersonList();
            newPerson = new Person();
        }
        return "person.xhtml";
    }

    public String deletePerson(Person pers){
        if(service.delete(pers)){
            loadPersonList();
        }
        return "person.xhtml";
    }
    
    private void loadPersonList(){
        personList = service.searchAllPerson();
    }
    
    /**
     * @return the sdtF
     */
    public SimpleDateFormat getSdtF() {
        return sdtF;
    }
    
    
  
    
}

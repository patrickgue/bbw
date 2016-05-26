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

import ch.eggbacon.app.entity.Leistung;
import ch.eggbacon.app.interf.LeistungService;
import ch.eggbacon.app.service.LeistungServiceImpl;
import ch.eggbacon.app.service.PersonServiceImpl;
import ch.eggbacon.app.util.Constants;
import ch.eggbacon.util.logger.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author guenthard
 */
@ManagedBean
@SessionScoped
public class LeistungController {
    
    private Logger LOG = new Logger(this.getClass().getName()); 

    
    private List<Leistung> leistungList;
    private LeistungService service;
    private Leistung newLeistung;

    public LeistungController () {
        
        service = new LeistungServiceImpl();
        
        newLeistung = new Leistung();
        loadLeistungList();
    }

    
    public String createNewLeistung(){
        if(service.persist(newLeistung)){
            loadLeistungList();
            newLeistung = new Leistung();
        }
        return Constants.LEISTUNG_SITE;
    }

    public String deleteLeistung(Leistung leistung){
        if(service.delete(leistung)){
            loadLeistungList();
        }
        return Constants.LEISTUNG_SITE;
    }
    
    private void loadLeistungList(){
        leistungList = service.getAllLeistung();
    }

    public List<Leistung> getLeistungList() {
        return leistungList;
    }

    public void setLeistungList(List<Leistung> leistungList) {
        this.leistungList = leistungList;
    }

    public Leistung getNewLeistung() {
        return newLeistung;
    }

    public void setNewLeistung(Leistung newLeistung) {
        this.newLeistung = newLeistung;
    }
    
    
    
    
}

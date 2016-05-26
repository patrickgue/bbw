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
import ch.eggbacon.app.entity.Preis;
import ch.eggbacon.app.interf.LeistungService;
import ch.eggbacon.app.interf.PreisService;
import ch.eggbacon.app.service.LeistungServiceImpl;
import ch.eggbacon.app.service.PreisServiceImpl;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author guenthard
 */
@ManagedBean
@SessionScoped
public class PreisController {
    
    private Leistung selectedLeistung;
    private List<Leistung> leistungList;
    private List<Preis> preise;
    
    private PreisService service;
    private LeistungService leistungService;
    
    public PreisController() {
        leistungService = new LeistungServiceImpl();
        service = new PreisServiceImpl();
        
        leistungList = leistungService.getAllLeistung();        
    }

    public Leistung getSelectedLeistung() {
        return selectedLeistung;
    }

    public void setSelectedLeistung(Leistung selectedLeistung) {
        this.selectedLeistung = selectedLeistung;
    }

    public PreisService getService() {
        return service;
    }

    public void setService(PreisService service) {
        this.service = service;
    }

    public List<Leistung> getLeistungList() {
        return leistungList;
    }

    public void setLeistungList(List<Leistung> leistungList) {
        this.leistungList = leistungList;
    }

    public List<Preis> getPreise() {
        return preise;
    }

    public void setPreise(List<Preis> preise) {
        this.preise = preise;
    }
    
}

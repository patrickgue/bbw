/*
 * Copyright (C) 2016 Zoe
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

import ch.eggbacon.app.entity.Buchung;
import ch.eggbacon.app.interf.BuchungService;
import ch.eggbacon.app.service.BuchungServiceImpl;
import ch.eggbacon.app.util.Constants;
import ch.eggbacon.util.logger.Logger;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Zoe
 */
public class BuchungController {
    
    private Logger log = new Logger(this.getClass().getName());
    
    private List<Buchung> buchungList;
    
    private BuchungService service;
    private Buchung newBuchung;
    
    public BuchungController(){
         service = new BuchungServiceImpl();
         newBuchung = new Buchung();
         loadBuchungList();
    }
    
    public void loadBuchungList(){
     buchungList = service.searchAllBuchung();
    }
}


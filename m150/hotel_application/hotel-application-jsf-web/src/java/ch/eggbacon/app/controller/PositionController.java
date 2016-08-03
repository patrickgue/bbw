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
package ch.eggbacon.app.controller;

import ch.eggbacon.app.entity.Buchung;
import ch.eggbacon.app.entity.Position;
import ch.eggbacon.app.interf.BuchungService;
import ch.eggbacon.app.interf.PositionService;
import ch.eggbacon.app.service.BuchungServiceImpl;
import ch.eggbacon.app.service.PositionServiceImpl;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Patrick
 */
@ManagedBean
@SessionScoped
public class PositionController {
    
    private PositionService positionService;
    private BuchungService buchungService;
    
    private List<Position> positionList;
    private List<Buchung> buchungen;
    
    private Position newPosition;
    
    public PositionController() {
        positionService = new PositionServiceImpl();
        buchungService = new BuchungServiceImpl();
        
        buchungen = buchungService.searchAllBuchung();
        loadList();
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
    
    private void loadList(){
        positionList = positionService.searchAllPosition();
        newPosition = positionList.get(0);
    }
    
    public String delete(Position pos){
        positionService.delete(pos);
        loadList();
        return "position.xhtml";
    }

    public Position getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(Position newPosition) {
        this.newPosition = newPosition;
    }
    
    
    public String saveNewPosition(){
        return "position.xhtml";
    }
        
    public List<Buchung> getBuchungen() {
        return buchungen;
    }

    public void setBuchungen(List<Buchung> buchungen) {
        this.buchungen = buchungen;
    }
        
    
}


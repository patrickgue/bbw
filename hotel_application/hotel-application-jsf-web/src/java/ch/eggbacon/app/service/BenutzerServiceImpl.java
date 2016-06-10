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
package ch.eggbacon.app.service;

import ch.eggbacon.app.entity.Benutzer;
import ch.eggbacon.app.entity.Buchung;
import ch.eggbacon.app.interf.BenutzerService;
import ch.eggbacon.app.interf.BuchungService;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class BenutzerServiceImpl extends DatabaseServiceImpl<Benutzer> implements BenutzerService{

    private final String TABLE_NAME = "Benutzer";

    @Override
    public List<Benutzer> searchAllBenutzer() {
        return getSession().createQuery("FROM " +  TABLE_NAME).list();
    }
    
   
    
}

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

import ch.eggbacon.app.entity.Leistung;
import ch.eggbacon.app.interf.LeistungService;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Patrick
 */
public class LeistungServiceImpl extends DatabaseServiceImpl<Leistung> implements LeistungService{

    private final String TABLE_NAME = "Leistung";
    
    @Override
    public List<Leistung> getAllLeistung() {
        return getSession().createQuery("FROM " +  TABLE_NAME).list();
    }
    
    
    @Override
    public Leistung getLeistungByBeschreibung(String beschreibung) {
        Query q = getSession().createQuery("FROM " + TABLE_NAME + " WHERE Beschreibung = :beschr");
        q.setParameter("beschr", beschreibung);
        return ((Leistung)q.uniqueResult());
    }

    @Override
    public Leistung getLeistungById(Long id) {
        Query q = getSession().createQuery("FROM" + TABLE_NAME + "WHERE LeistungID = :id");
        
        return (Leistung)q.list().get(0);
    }
    
    
    
}

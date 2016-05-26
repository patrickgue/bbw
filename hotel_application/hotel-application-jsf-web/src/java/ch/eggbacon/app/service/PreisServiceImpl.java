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
import ch.eggbacon.app.entity.Preis;
import ch.eggbacon.app.interf.LeistungService;
import ch.eggbacon.app.interf.PreisService;
import ch.eggbacon.util.logger.Logger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Patrick
 */
public class PreisServiceImpl extends DatabaseServiceImpl<Preis> implements PreisService{

    private Logger LOG = new Logger(this.getClass().getName());
    
    private final String TABLE_NAME = "Preis";

    @Override
    public List<Preis> getAllPreis() {
        LOG.info("Get all Preis");
        return getSession().createQuery("FROM " + TABLE_NAME).list();
    }

    @Override
    public List<Preis> getPreisByLeistung(Leistung l) {
        Query q = getSession().createQuery("FROM " + TABLE_NAME + " WHERE LeistungID = :leistungid" );
        q.setParameter("leistungid", + l.getLeistungId());
        return q.list();
    }

    @Override
    public List<Preis> getPreisByDatum(Date d) {
        Query q = getSession().createQuery("FROM " + TABLE_NAME + " WHERE Datum = :datum");
        q.setDate("datum", d);
        return q.list();
    }
    
    @Override
    public boolean addPreis(Preis p){
        try {
            Calendar c = new GregorianCalendar();

            c.setTime(p.getDatum());

            c.set(Calendar.HOUR, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);

            p.setDatum(c.getTime());
        } catch (Exception e) {
            return false;
        }
        return persist(p);
    }
    
}

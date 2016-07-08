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

import ch.eggbacon.app.entity.Buchung;
import ch.eggbacon.app.entity.Person;
import ch.eggbacon.app.entity.Rechnung;
import ch.eggbacon.app.interf.BuchungService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.hibernate.Query;
import ch.eggbacon.app.interf.RechnungService;

/**
 *
 * @author Patrick
 */
public class RechungServiceImpl extends DatabaseServiceImpl<Rechnung> implements RechnungService{

    @Override
    public List<Rechnung> searchRechnungenByPersonId(Long id) {
        BuchungService buchungService = new BuchungServiceImpl();
        List<Buchung> buchungen = buchungService.searchBuchungenByPersonId(id);
        List<Rechnung> rechnungen = new ArrayList<>();
        for(Buchung b : buchungen) {
            Query q = getSession().createQuery("FROM Rechnung WHERE buchungId = :buchungId");
            q.setLong("buchungId", b.getPerson().getPersId());
            rechnungen.add((Rechnung) q.uniqueResult());
        }
        return rechnungen;
    }

    public List<Rechnung> searchAllRechnungen() {
        return getSession().createQuery("FROM Rechnung").list();
    }
    
    
}

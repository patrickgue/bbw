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
package ch.eggbacon.app.interf;

import ch.eggbacon.app.entity.Leistung;
import ch.eggbacon.app.entity.Preis;
import java.util.Date;
import java.util.List;

/**
 *
 * @author guenthard
 */
public interface PreisService extends DatabaseService<Preis>{
    public List<Preis> getAllPreis();
    public List<Preis> getPreisByLeistung(Leistung l);
    public List<Preis> getPreisByDatum(Date d);
    public boolean addPreis(Preis p);
    
}

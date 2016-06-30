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
package ch.eggbacon.app.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author guenthard
 */
public class TimeUtil {
    public static Date setYear(Date d, Integer year) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        c.set(Calendar.YEAR, year);
        return c.getTime();
    }
    
    public static Date setMonth(Date d, Integer month) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        c.set(Calendar.MONTH, month);
        return c.getTime();
    }
    
    public static Date setDay(Date d, Integer day) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }
}

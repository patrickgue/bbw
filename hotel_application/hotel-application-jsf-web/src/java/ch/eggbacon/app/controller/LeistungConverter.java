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

import ch.eggbacon.app.interf.LeistungService;
import ch.eggbacon.app.service.LeistungServiceImpl;
import java.lang.annotation.Annotation;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/*
 *
 * @author Patrick
 */
@ManagedBean
@FacesConverter
public class LeistungConverter implements  Converter{

    private LeistungService service;

    public LeistungConverter() {
        service = new LeistungServiceImpl();
    }
    
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null; //service.get
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return "";//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

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
import ch.eggbacon.app.entity.Person;
import ch.eggbacon.app.entity.Rechnung;
import ch.eggbacon.app.interf.BuchungService;
import ch.eggbacon.app.interf.RechnungService;
import ch.eggbacon.app.pdf.PDFCreator;
import ch.eggbacon.app.service.BuchungServiceImpl;
import ch.eggbacon.app.service.RechungServiceImpl;
import ch.eggbacon.app.util.Constants;
import ch.eggbacon.util.logger.Logger;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 *
 * @author Zoe
 */
@ManagedBean
@SessionScoped
public class RechnungController {
    
    private Logger log = new Logger(this.getClass().getName());
    
    private List<Rechnung> rechnungen;
    
    private RechnungService service;
    
    private Person absender;
    
    public RechnungController(){
         service = new RechungServiceImpl();
         loadRechnungList();
         absender = new Person();
         absender.setAnrede("Frau/Herr");
         absender.setVorname("Jamie");
         absender.setNachname("Doe");
         absender.setStrasse("Egg & Bacon Hotel");
         absender.setOrt("8400 Winterthur");
         absender.setPrivattelefon("+41 52 123 45 67");
    }
    
    public void loadRechnungList(){
        setRechnungen(service.searchAllRechnungen());
    }

    public List<Rechnung> getRechnungen() {
        return rechnungen;
    }

    public void setRechnungen(List<Rechnung> rechnungen) {
        this.rechnungen = rechnungen;
    }
    
    public String getRechnungPDF(Rechnung r) throws IOException{
        String filename = "E:/rechnung.pdf";
        PDFCreator creator = new PDFCreator();
        PDPageContentStream stream = creator.addPage();
        Person []addre = {
            absender,
            r.getBuchung().getPerson()
        };
        
        String[] str = {
            " - " + r.getBuchung().toString(),
            " - Erstellungsdatum" + r.getErstellungsDatum(),
            " - Zahlungsfrist" + r.getErstellungsDatum()
        };
        creator.drawTitlePage("Rechung", addre, str, stream);
        stream.close();
        
        stream = creator.addPage();
        String[][] tmpStr = {
            {r.getBuchung().getAbreise().toString(), r.getBuchung().getAbreise().toString()}
        };
        
        creator.drawTablePage(stream, tmpStr);
        stream.close();
        
        creator.save(filename);
        

        
        return "rechnung";
    }

    
    

}


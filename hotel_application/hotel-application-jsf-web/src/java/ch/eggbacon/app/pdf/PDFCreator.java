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
package ch.eggbacon.app.pdf;

import ch.eggbacon.app.entity.Person;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author Patrick
 */
public class PDFCreator {
    private int pageCounter = 1;
    private PDDocument doc;
        
    public PDFCreator() {
        doc = new PDDocument();
    }
    
    public void drawHeader(PDPageContentStream contentStream) throws IOException{
        
        PDImageXObject img = PDImageXObject.createFromFile("web/img/logo.png", doc);
        
        contentStream.drawImage(img, PDFConstants.MARGIN,PDFConstants.PAPER_HEIGHT - 40,47,30);
        
        contentStream.setFont( PDType1Font.HELVETICA_BOLD , 18 );
        contentStream.beginText();
        contentStream.moveTextPositionByAmount(PDFConstants.MARGIN + 50,PDFConstants.PAPER_HEIGHT - 30);
        contentStream.drawString("Egg & Bacon Hotel");
        contentStream.endText();
    }
    
    public void drawFooter(int pageNr, PDPageContentStream contentStream) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.beginText();
        contentStream.moveTextPositionByAmount(PDFConstants.MARGIN, 20);
        contentStream.drawString("Page " + pageNr);
        contentStream.endText();
    }
    
    
    public void drawTitlePage(String title, Person[] addr, String[] strs,PDPageContentStream contentStream) throws IOException{
        
        
        float ypos = PDFConstants.PAPER_HEIGHT - PDFConstants.MARGIN;
        
        contentStream.beginText();
        
        // Title
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.moveTextPositionByAmount(PDFConstants.MARGIN, ypos);
        contentStream.drawString(title);
        contentStream.endText();
        ypos-=20;
        
        // Adressen
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        for(Person p : addr) {
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(PDFConstants.MARGIN, ypos-=13);
            contentStream.drawString(p.getAnrede());
            contentStream.endText();
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(PDFConstants.MARGIN, ypos-=13);
            contentStream.drawString(p.getVorname() + " " + p.getNachname());
            contentStream.endText();
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(PDFConstants.MARGIN, ypos-=13);
            contentStream.drawString(p.getStrasse());
            contentStream.endText();
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(PDFConstants.MARGIN, ypos-=13);
            contentStream.drawString(p.getOrt());
            contentStream.endText();
        
            ypos -= 25;
        }
        
        
        
        
        
        
        
    }
    
    public void drawTablePage(PDPageContentStream contentStream, String[][] content) throws IOException {
        final float y = PDFConstants.PAPER_HEIGHT - PDFConstants.MARGIN;
        final int margin = PDFConstants.MARGIN;
        final int rows = content.length;
        final int cols = content[0].length;
        final float rowHeight = 20f;
        final float tableWidth = PDFConstants.PAPER_WIDTH - (2 * margin);
        final float tableHeight = rowHeight * rows;
        final float colWidth = tableWidth/(float)cols;
        final float cellMargin=5f;

        
        
        //draw the rows
        float nexty = y ;
        for (int i = 0; i <= rows; i++) {
            contentStream.drawLine(margin, nexty, margin+tableWidth, nexty);
            nexty-= rowHeight;
        }

        //draw the columns
        float nextx = margin;
        for (int i = 0; i <= cols; i++) {
            contentStream.drawLine(nextx, y, nextx, y-tableHeight);
            nextx += colWidth;
        }

        //now add the text
        contentStream.setFont( PDType1Font.HELVETICA , 12 );

        float textx = margin+cellMargin;
        float texty = y-15;
        for(int i = 0; i < content.length; i++){
            for(int j = 0 ; j < content[i].length; j++){
                String text = content[i][j];
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(textx,texty);
                contentStream.drawString(text);
                contentStream.endText();
                textx += colWidth;
            }
            texty-=rowHeight;
            textx = margin+cellMargin;
        }
    }
    
    public PDPageContentStream addPage(int pageNumber) throws IOException{
        PDPage page = new PDPage(PDFConstants.PAPER_SIZE);
        doc.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
        drawHeader(contentStream);
        drawFooter(pageNumber, contentStream);
        
        return contentStream;
    }
    
    public PDPageContentStream addPage() throws IOException {
        return addPage(pageCounter++);
    }
    
    public void save(String path) throws IOException{
        doc.save(path);
    }
    
    public static void main(String[] args) throws IOException {
        
        PDFCreator document = new PDFCreator();
        

        String[][] content = {
            {"Buchung"," Person ", " Preis "},
            {"123","Person1", "100.35"},
            {"456","Person2", "624.20"},
            {"123","Person1", "100.35"},
            {"456","Person2", "624.20"},
            {"123","Person1", "100.35"},
            {"456","Person2", "624.20"},
            {"123","Person1", "100.35"},
            {"456","Person2", "624.20"},
            {"123","Person1", "100.35"},
            {"456","Person2", "624.20"},
            {"123","Person1", "100.35"},
            {"456","Person2", "624.20"},
            {"123","Person1", "100.35"},
            {"456","Person2", "624.20"},
            {"123","Person1", "100.35"},
            {"456","Person2", "624.20"},
            {"123","Person1", "100.35"},
            {"456","Person2", "624.20"}
        } ;

        PDPageContentStream firstPageStream = document.addPage();
        
        document.drawTablePage(firstPageStream, content);
        firstPageStream.close();

        PDPageContentStream secondPageStream = document.addPage();
        
        
        String[] strings = {};
        
        Person p = new Person();
        p.setAnrede("Herr");
        p.setVorname("Patrick");
        p.setNachname("Guenthard");
        p.setStrasse("Roggenfarstr. 17d");
        p.setOrt("8193 Eglisau");
        
        Person[] addresses = {
            p,
            p
        }; 
        document.drawTitlePage("Rechnung", addresses, strings, secondPageStream);
        secondPageStream.close();
        
        
        document.save("web/tmp/test.pdf" );
    } 
}

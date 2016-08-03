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
package ch.eggbacon.app.pdf;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author guenthard
 */
public class PDFConstants {
    public final static int MARGIN = 50;
    public final static PDRectangle PAPER_SIZE = PDRectangle.A4;
    public final static float PAPER_WIDTH = PAPER_SIZE.getWidth();
    public final static float PAPER_HEIGHT = PAPER_SIZE.getHeight();
}

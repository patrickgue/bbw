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
package ch.eggbacon.util.logger;

/**
 *
 * @author guenthard
 */
public class Logger {
    private String classname;

    public Logger(String classname) {
        this.classname = classname;
    }
    
    
    
    
    public void info(String info){
        System.out.println("[info:"+classname+"]"+info);
    }
    
    public void debug(String debug){
        System.out.println("[debug:"+classname+"]"+debug);
    }
    
    public void error(String error){
        System.err.println("[error:"+classname+"]"+error);
    }
}

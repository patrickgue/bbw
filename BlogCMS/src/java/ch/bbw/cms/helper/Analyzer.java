/* 
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * This class is used to collect data and save it to a file
 * <i>(you may have to change the path of the csv file if you are working on a Microsoft Windows NT Machine)</i>
 */
public class Analyzer {
    private HashMap<String, Integer> map = new HashMap<>();
    private File dbSave;
    long lastModified;
    
    public Analyzer(String fileName){
        dbSave = new File("/tmp/analyzer."+fileName+".db.csv");
        loadFile();
        
    }
    
    private void loadFile(){
        try{
            if(dbSave.exists()){
                BufferedReader reader = new BufferedReader(new FileReader(dbSave));
                String buffer;
                while((buffer = reader.readLine()) != null){
                    String[] splitedLine = buffer.split(",");
                    map.put(splitedLine[0], (int) Integer.parseInt(splitedLine[1]));
                }
            } else {
                dbSave.createNewFile();
            } 
        } catch(FileNotFoundException ex) {
        } catch(IOException ex){
        }
    }
    
    public boolean count(String key){
        if(dbSave.lastModified() != lastModified){
            loadFile();
        }
        if(!map.containsKey(key)){
            add(key);
        }
        map.put(key, map.get(key) + 1);
        return save();
    }
    
    private boolean save(){
        String saveString = "";
        
        for(Iterator it = map.keySet().iterator(); it.hasNext();){
            String key = (String) it.next() ;
            saveString += key +","+map.get(key)+"\n";
        }
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(dbSave));
            writer.write(saveString);
            writer.close();
            lastModified = new Date().getTime();
            return true;
        } catch(IOException ex){
            return false;
        }
    }
    
    public void add(String name){
        map.put(name, 0);
    }
    
    
}

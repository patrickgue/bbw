/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.helper;

import java.util.ArrayList;

/**
 *
 * @author guenthard
 */

public class ClosedList<T>{
    private ArrayList<T> list = new ArrayList<T>();
    private int currentIndex = 0;

    public ClosedList(T[] filesStr){
        for(T tmp : filesStr){
            list.add(tmp);
        }
    }
    
    public ClosedList(ArrayList<T> lst){
        list = lst;
    }
    
    public ClosedList(){
        list = new ArrayList();
    }
    
    public void add(T obj){
        list.add(obj);
    }
    
    public void remove(T obj){
        list.remove(obj);
    }
    
    

    public T next(){
        T rtrn = list.get(currentIndex);

        if(currentIndex < list.size() -1){
            currentIndex++;
        } else {
            currentIndex = 0;
        }

        return rtrn;

    }
}


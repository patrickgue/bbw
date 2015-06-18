/* 
 * @author: 5ia13paguenthard
 * 
 * Licensed under the GNU GPL v3
 * NO WARRANTY
 */
package ch.bbw.cms.helper;

import java.util.ArrayList;

/**
 *
 * List which can restart the iterator
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


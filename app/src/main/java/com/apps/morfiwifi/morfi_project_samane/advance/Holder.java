package com.apps.morfiwifi.morfi_project_samane.advance;

import java.util.List;

public class Holder<T extends entity> {
    List<T> list ;


    public Holder(List<T> listi ){
        list = listi;
    }

    /*public String get_first ( int index ){
        return list.get(index).get_first();
    }

    public String get_second ( int index ){
        return list.get(index).get_second();
    }

    public String get_third ( int index){
        return list.get(index).get_third();
    }*/

    public T get(int index){
        return list.get(index);
    }

    public T first(){
        return list.get(0);
    }

    public T last(){
        return list.get(list.size()-1);
    }

    public int size(){
        return list.size();
    }
}

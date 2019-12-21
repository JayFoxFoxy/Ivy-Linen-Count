package com.bitinbyte.ivycambridgebrassery;

import android.app.AlertDialog;
import android.provider.MediaStore;

import java.util.ArrayList;

import static android.app.AlertDialog.*;

public class Articles {

    private String name;
    private int totalNumber;

    public Articles(){
        //Empty Constructor
    }

    public Articles(String name){
        this.name = name;
        this.totalNumber = 0;
    }

    public String showArticles(ArrayList<Articles> ls){

        String message = "";

        for(int i = 0; i < ls.size(); i ++){
            message += ls.get(i).getName();
            message += " - ";
            message += ls.get(i).getTotalNumber();
            message += "\n";
            //System.out.println(ls.get(i).getName() + " - " + ls.get(i).getTotalNumber() + "\n");
        }

        return message;
    }

    public void plusTotalNumber(ArrayList<Articles> ls, int id, int plus){

        int total = ls.get(id).getTotalNumber() + plus;

        ls.get(id).setTotalNumber(total);

    }

    public void lessTotalNumber(ArrayList<Articles> ls, int id, int less){

        int total = ls.get(id).getTotalNumber() - less;

        ls.get(id).setTotalNumber(total);

    }

    public ArrayList<Articles> deleteValue(ArrayList<Articles> ls, String name){

        boolean check = false;

        int id = -1;
        for(int i = 0; i < ls.size(); i++){
            if(ls.get(i).getName().equals(name)){
                ls.remove(i);
                check = true;
            }
        }

        return ls;
    }

    public String getName(){ return this.name; }
    public int getTotalNumber(){ return this.totalNumber; }

    public void setName(String name){ this.name = name; }
    public void setTotalNumber(int totalNumber){ this.totalNumber = totalNumber; }
}

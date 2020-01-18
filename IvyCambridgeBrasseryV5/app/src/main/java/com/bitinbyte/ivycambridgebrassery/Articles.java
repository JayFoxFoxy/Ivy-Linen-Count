package com.bitinbyte.ivycambridgebrassery;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Articles extends AppCompatActivity {



    public static class ArticleModel
    {
        private final String name;
        private final int totalNumber;

        ArticleModel(String name, int totalNumber)
        {
            this.name = name;
            this.totalNumber = totalNumber;
        }

        public String getName() { return name; }
        public int getTotalNumber() { return totalNumber; }
    }

    private String name;
    private int totalNumber;

    public Articles(){
        //Empty Constructor
    }

    public Articles(String name, int totalNumber){
        this.name = name;
        this.totalNumber = totalNumber;
    }

    public String showArticles(ArrayList<Articles> ls){

        String message = "";

        for(int i = 0; i < ls.size(); i ++){
            message += ls.get(i).getName();
            message += " - ";
            message += ls.get(i).getTotalNumber();
            message += "\n";
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

    public void MultValue(ArrayList<Articles> ls, int id, int num){

        int total = ls.get(id).getTotalNumber() + (num * 20);

        ls.get(id).setTotalNumber(total);

    }

    public void reset(){

        Container cn = new Container();

        for (int i = 0; i < cn.getContainer().size(); i++){
            cn.getContainer().get(i).setTotalNumber(0);
        }

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

    public ArticleModel toModel()
    {
        return new ArticleModel(name, totalNumber);
    }

    public String getName(){ return this.name; }
    public int getTotalNumber(){ return this.totalNumber; }

    public void setName(String name){ this.name = name; }
    public void setTotalNumber(int totalNumber){ this.totalNumber = totalNumber; }
}

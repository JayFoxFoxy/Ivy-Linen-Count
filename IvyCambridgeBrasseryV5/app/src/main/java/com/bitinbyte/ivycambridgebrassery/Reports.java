package com.bitinbyte.ivycambridgebrassery;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Reports {

    private String report;
    private String date;

    public Reports(){
        //Empty Constructor
    }

    public Reports(String report, String date){
        this.report = report;
        this.date = date;
    }

    public String generateReport(ArrayList<Articles> ls) {

        String message = "";
        String date = "";

        for(int i = 0; i < ls.size(); i ++){
            message += ls.get(i).getName();
            message += " - ";
            message += ls.get(i).getTotalNumber();
            message += "\n";
        }

        return message;
    }

    public String generateDate(){

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        return formattedDate;
    }

    public void saveFile(Context context){

        newCount nc = new newCount();


        SharedPreferences prefs = context.getSharedPreferences("reports", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(nc.getReports());
        editor.putString("reports", json);
        editor.apply();
    }

    public void readFile(Context context){

        newCount nc = new newCount();

        File f = new File("/data/data/com.bitinbyte.ivycambridgebrassery/shared_prefs/reports.xml");
        if (f.exists()) {
            Log.d("TAG", "SharedPreferences Name_of_your_preference : exist");
            SharedPreferences prefs = context.getSharedPreferences("reports", Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = prefs.getString("reports", "");
            Type type = new TypeToken<ArrayList<Reports>>(){}.getType();
            List<Reports> reports = new Gson().fromJson(json, type);
            nc.getReports().clear();
            for(int i = 0; i < reports.size(); i++){
                Reports r = new Reports(reports.get(i).getReport(), reports.get(i).getDate());
                //Articles a = new Articles(articleModels.get(i).getName(), articleModels.get(i).getTotalNumber());
                nc.getReports().add(r);
            }
        }else {
            Log.d("TAG", "Setup default preferences");
        }
    }

    public String getReport(){ return this.report; }
    public String getDate(){ return this.date; }
}

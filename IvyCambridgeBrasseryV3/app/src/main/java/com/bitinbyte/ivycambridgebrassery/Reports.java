package com.bitinbyte.ivycambridgebrassery;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Reports {

    private String report;
    private String date;

    public Reports(){
        //Empty Constructor
    }

    public Reports(String report){
        this.report = report;
        this.date = generateDate();
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

    public String getReport(){ return this.report; }
    public String getDate(){ return this.date; }
}

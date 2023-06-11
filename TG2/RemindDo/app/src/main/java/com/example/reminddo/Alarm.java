package com.example.reminddo;

import android.content.Context;

import java.io.Serializable;
import java.util.Date;

public class Alarm implements Serializable {

    private  int hour;
    private  String name;
    private  Date date;
    private  String description;
    private  int minutes;

    public Alarm(String name, Date date, String description,int hour, int minutes) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.hour = hour;
        this.minutes = minutes;
    }
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }



}

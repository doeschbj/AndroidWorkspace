package com.example.lovecounter;

import android.graphics.drawable.Drawable;

public class BackgroundHolder {
    private Drawable bckgr;
    private String date;

    public BackgroundHolder(Drawable bckgr, String date){
        setBckgr(bckgr);
        setDate(date);
    }

    public Drawable getBckgr() {
        return bckgr;
    }

    public void setBckgr(Drawable bckgr) {
        this.bckgr = bckgr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

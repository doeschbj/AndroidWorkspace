package com.example.einkaufsliste;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.util.ArrayList;

public class DataBaseHolder {
    private Context context;

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7243303";
    private static final String user = "sql7243303";
    private static final String password = "YZnyUFhcKJ";
    private static DataBaseHolder unique = null;
    private Connection con;


    public DataBaseHolder(Context context){
        this.context = context;
    }

    public ArrayList<Eintrag> getData(){
        ArrayList<Eintrag> data = new ArrayList<Eintrag>();

        return data;
    }

    public void addEntry(String name, String anzahl){
        new Thread(new Runnable() {
            public void run() {
                try {

                    this.wait(200);
                }catch(InterruptedException e){

                }
            }
        }).start();
    }

    public void removeEntry(String name){
        new Thread(new Runnable() {
            public void run() {
                try {

                    this.wait(200);
                }catch(InterruptedException e){

                }
            }
        }).start();
    }

    public Context getContext(){
        return context;
    }
}

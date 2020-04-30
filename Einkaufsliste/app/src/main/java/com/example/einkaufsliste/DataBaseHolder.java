package com.example.einkaufsliste;

import android.content.Context;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DataBaseHolder {
    private Context context;

    private static final String driver = "net.sourceforge.jtds.jdbc.Driver";
    private static final String url = "";
    private static final String user = "bjoern";
    private static final String password = "bj123";
    private static DataBaseHolder unique = null;
    private Connection con;


    public DataBaseHolder(Context context)throws ClassNotFoundException , SQLException {
        this.context = context;
        if(unique == null) {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        }
    }

    public void connect()throws ClassNotFoundException , SQLException {
        if(con == null && unique == null) {
            Class.forName(driver);
            System.out.println("Driver loaded");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established");
        }
    }

    public void close() throws SQLException {
        if(con != null) {
            //Statement st = con.createStatement();
            con.close();
        }
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

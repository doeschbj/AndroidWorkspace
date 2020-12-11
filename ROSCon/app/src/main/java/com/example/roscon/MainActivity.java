package com.example.roscon;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.roscon.ui.main.SectionsPagerAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private int r_nmbr = 0;
    private static final int SERVERPORT = 50007;
    private static final String SERVER_IP = "192.168.0.110";
    private Socket socket;
    private boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread conThread = new Thread()
        {
            public void run()
            {
                // hier server nach anzahl der robos fragen
                try {
                    InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                    socket = new Socket(serverAddr, SERVERPORT);
                    Log.d("Mainac_moin danach", "Con established");
                    connected = true;
                    PrintWriter out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())),
                            true);
                    BufferedReader input;
                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    out.println("2");
                    Log.d("Mainac_moin danach", "Con 1");
                    String str = input.readLine();
                    Log.d("Mainac_moin danach", "Con 2");
                    out.println("2");
                    str = input.readLine();

                    r_nmbr = Integer.parseInt(str);
                    input.close();
                    out.close();
                    socket.close();
                    connected = false;
                }catch(IOException e1){
                    e1.printStackTrace();
                }
            }
        };
        try{
            conThread.start();
            conThread.join();
        }catch(InterruptedException e1){
            e1.printStackTrace();
        }

        if(r_nmbr < 1){
            //return;
        }
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), r_nmbr);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onDestroy(){
        if(connected){
            try{
                socket.close();
            }catch(IOException e1){
                e1.printStackTrace();
            }
        }
        super.onDestroy();

    }
}
